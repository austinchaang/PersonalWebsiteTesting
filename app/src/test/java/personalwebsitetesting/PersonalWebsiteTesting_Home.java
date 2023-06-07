/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package personalwebsitetesting;

import pages.HomePage;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.net.MalformedURLException;

public class PersonalWebsiteTesting_Home {
    String webDriverPath = "/usr/local/bin/chromedriver";
    WebDriver driver;
    HomePage homePage = new HomePage();
    String homeURL = "https://austinchang.ca/";
    String projectsURL = "https://austinchang.ca/projects";
    String interestsURL = "https://austinchang.ca/interests";
    String linkedInURL = "https://www.linkedin.com/in/austinjchang/";
    String gitHubURL = "https://github.com/austinchaang";
    String emailHREF = "mailto:changaustinj@gmail.com";
    String resumeURL = "https://www.austinchang.ca/static/mysite/austinChangResume.pdf";
    WebDriverWait wait;
    PDDocument doc;
    JavascriptExecutor jsExecutor;



    private static int getStatusCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            connection.disconnect();
            return statusCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String readPdfContent(String url) throws IOException {
        URL pdfUrl = new URL(url);
		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		doc.close();
        return content;
    }

    @Before public void setup() {
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(homeURL);
        wait = new WebDriverWait(driver, 10);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @After public void tearDown() {
        driver.quit();
    }

    // WT1: HTTP 200 status code 
    @Test public void homeHTTP200() {
        int statusCode = getStatusCode(homeURL);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    //WT2: Testing home URL link
    @Test public void homeURL() {
        // Wait for the homeURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getHomeURL()));
        // Click on the homeURL element
        driver.findElement(homePage.getHomeURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(homeURL, driver.getCurrentUrl());
    }
    
    //WT3: Testing projects URL link
    @Test public void projectsURL() {
        // Wait for the projectsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getProjectsURL()));
        // Click on the projectsURL element
        driver.findElement(homePage.getProjectsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(projectsURL, driver.getCurrentUrl());
    }

    //WT4: Testing interests URL link
    @Test public void interestsURL() {
        // Wait for the interestsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getInterestsURL()));
        // Click on the interestsURL element
        driver.findElement(homePage.getInterestsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(interestsURL, driver.getCurrentUrl());
    }

    //WT5: Testing resume button link opens in a new tab and verifying the content
    @Test public void resumeButton() {
        String homeWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getResume()));
        driver.findElement(homePage.getResume()).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(homeWindowHandle)) {
                driver.switchTo().window(handle);
                // Check if the new tab is open by verifying its page title
                if (driver.getTitle().contains("austinChangResume.pdf")) {
                    // New tab is open, perform verifications of resume
                    try {
                        String content = readPdfContent(driver.getCurrentUrl());
                        Assert.assertTrue(content.contains("Austin Chang"));
                        Assert.assertTrue(content.contains("Work History"));
                        Assert.assertTrue(content.contains("Technical Projects"));
                        Assert.assertTrue(content.contains("Languages"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                }
                // Close the new tab/window after verification
                driver.close();
            }
        }
    }

    //WT6: Testing page numbers of resume to access resume element
    @Test public void resumePages() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getResume()));
        try {
            readPdfContent(resumeURL);
            int resumePages = homePage.getPageCount(doc);
            Assert.assertEquals(resumePages, 1);
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    //WT7: Testing linkedIn button link opens in a new tab correctly
    @Test public void linkedInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getLinkedInButton()));
        WebElement linkedInButton = driver.findElement(homePage.getLinkedInButton());
        String href = linkedInButton.getAttribute("href");
        // Scroll to location of linkedIn button
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", linkedInButton);
        // Click href of linkedIn button
        jsExecutor.executeScript("window.open(arguments[0])", href);
        String homeWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(homeWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().startsWith(linkedInURL));
        driver.switchTo().window(homeWindowHandle);
    }

    //WT8: Testing GitHub button link opens in a new tab correctly
    @Test public void gitHubButton() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getGitHubButton()));
        WebElement gitHubButton = driver.findElement(homePage.getGitHubButton());
        String href = gitHubButton.getAttribute("href");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", gitHubButton);
        jsExecutor.executeScript("window.open(arguments[0])", href);
        String homeWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(homeWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().startsWith(gitHubURL));
        driver.switchTo().window(homeWindowHandle);
    }

    //WT9: Testing email button prompts user to open email app
    @Test public void emailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(homePage.getEmailButton()));
        WebElement emailButton = driver.findElement(homePage.getEmailButton());
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", emailButton);
        String href = emailButton.getAttribute("href");
        Assert.assertEquals(emailHREF, href);
    }

}