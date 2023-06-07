package personalwebsitetesting;

import pages.ProjectsPage;
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

public class PersonalWebsiteTesting_Projects {
    String webDriverPath = "/usr/local/bin/chromedriver";
    WebDriver driver;
    ProjectsPage projectsPage = new ProjectsPage();
    String homeURL = "https://austinchang.ca/";
    String projectsURL = "https://austinchang.ca/projects";
    String interestsURL = "https://austinchang.ca/interests";
    String websiteV1URL = "https://austinchaang.github.io/austinchangwebsiteversion1.github.io/";
    String websiteV1GitHubURL = "https://github.com/austinchaang/austinchangwebsiteversion1.github.io";
    String jsProjectURL = "https://austinchang.ca/jsproject";
    String jsProjectGitHubURL = "https://github.com/austinchaang/tictactoe";
    String linkedInURL = "https://www.linkedin.com/in/austinjchang/";
    String gitHubURL = "https://github.com/austinchaang";
    String emailHREF = "mailto:changaustinj@gmail.com";
    WebDriverWait wait;
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

    @Before public void setup() {
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(projectsURL);
        wait = new WebDriverWait(driver, 10);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @After public void tearDown() {
        driver.quit();
    }

    // WT10: HTTP 200 status code for the Project Page
    @Test public void projectsHTTP200() {
        int statusCode = getStatusCode(projectsURL);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    //WT11: Testing home URL link
    @Test public void homeURL() {
        // Wait for the homeURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getHomeURL()));
        // Click on the homeURL element
        driver.findElement(projectsPage.getHomeURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(homeURL, driver.getCurrentUrl());
    }
    
    //WT12: Testing projects URL link
    @Test public void projectsURL() {
        // Wait for the projectsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getProjectsURL()));
        // Click on the projectsURL element
        driver.findElement(projectsPage.getProjectsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(projectsURL, driver.getCurrentUrl());
    }

    //WT13: Testing interests URL link
    @Test public void interestsURL() {
        // Wait for the interestsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getInterestsURL()));
        // Click on the interestsURL element
        driver.findElement(projectsPage.getInterestsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(interestsURL, driver.getCurrentUrl());
    }

    //WT14: Testing personal website v1 project link
    @Test public void personalWebsiteV1Link() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getWebsiteV1Link()));
        WebElement websiteV1Link = driver.findElement(projectsPage.getWebsiteV1Link());
        String href = websiteV1Link.getAttribute("href");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", websiteV1Link);
        jsExecutor.executeScript("window.location.href = arguments[0]", href);
        Assert.assertEquals(websiteV1URL, driver.getCurrentUrl());
    }

    //WT15: Testing personal website v1 github link opens in a new tab correctly
    @Test public void personalWebsiteV1GitHubLink() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getWebsiteGitHubLink()));
        WebElement websiteV1GitHubLink = driver.findElement(projectsPage.getWebsiteGitHubLink());
        String href = websiteV1GitHubLink.getAttribute("href");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", websiteV1GitHubLink);
        jsExecutor.executeScript("window.open(arguments[0])", href);
        String homeWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(homeWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().startsWith(websiteV1GitHubURL));
        driver.switchTo().window(homeWindowHandle);
    }

    //WT16: Testing js project link
    @Test public void jsProjectLink() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getjsProjectLink()));
        WebElement jsProjectLink = driver.findElement(projectsPage.getjsProjectLink());
        String href = jsProjectLink.getAttribute("href");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", jsProjectLink);
        jsExecutor.executeScript("window.location.href = arguments[0]", href);
        Assert.assertEquals(jsProjectURL, driver.getCurrentUrl());
    }

    //WT17: Testing that js project github link opens in a new tab correctly
    @Test public void jsProjectGitHubLink() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getjsProjectGitHubLink()));
        WebElement jsProjectGitHubLink = driver.findElement(projectsPage.getjsProjectGitHubLink());
        String href = jsProjectGitHubLink.getAttribute("href");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", jsProjectGitHubLink);
        jsExecutor.executeScript("window.open(arguments[0])", href);
        String homeWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(homeWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().startsWith(jsProjectGitHubURL));
        driver.switchTo().window(homeWindowHandle);
    }

    //WT18: Testing linkedIn button link opens in a new tab correctly
    @Test public void linkedInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getLinkedInButton()));
        WebElement linkedInButton = driver.findElement(projectsPage.getLinkedInButton());
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

    //WT19: Testing GitHub button link opens in a new tab correctly
    @Test public void gitHubButton() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getGitHubButton()));
        WebElement gitHubButton = driver.findElement(projectsPage.getGitHubButton());
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

    //WT20: Testing email button prompts user to open email app
    @Test public void emailButton() {
        wait.until(ExpectedConditions.elementToBeClickable(projectsPage.getEmailButton()));
        WebElement emailButton = driver.findElement(projectsPage.getEmailButton());
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", emailButton);
        String href = emailButton.getAttribute("href");
        Assert.assertEquals(emailHREF, href);
    }
}