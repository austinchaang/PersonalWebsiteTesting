package personalwebsitetesting;

import pages.JSProject;
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

public class PersonalWebsiteTesting_JSProject {
    String webDriverPath = "/usr/local/bin/chromedriver";
    WebDriver driver;
    JSProject jsProject = new JSProject();
    String homeURL = "https://www.austinchang.ca/";
    String projectsURL = "https://www.austinchang.ca/projects";
    String interestsURL = "https://www.austinchang.ca/interests";
    String jsProjectURL = "https://www.austinchang.ca/jsproject";
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;
    WebElement topLeft;
    WebElement topCenter;
    WebElement topRight;
    WebElement middleLeft;
    WebElement middleCenter;
    WebElement middleRight;
    WebElement bottomLeft;
    WebElement bottomCenter;
    WebElement bottomRight;


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
        driver.get(jsProjectURL);
        wait = new WebDriverWait(driver, 10);
        jsExecutor = (JavascriptExecutor) driver;
        topLeft = driver.findElement(jsProject.getTopLeft());
        topCenter = driver.findElement(jsProject.getTopCenter());
        topRight = driver.findElement(jsProject.getTopRight());
        middleLeft = driver.findElement(jsProject.getMiddleLeft());
        middleCenter = driver.findElement(jsProject.getMiddleCenter());
        middleRight = driver.findElement(jsProject.getMiddleRight());
        bottomLeft = driver.findElement(jsProject.getBottomLeft());
        bottomCenter = driver.findElement(jsProject.getBottomCenter());
        bottomRight = driver.findElement(jsProject.getBottomRight());
    }

    @After public void tearDown() {
        driver.quit();
    }

    // WT21: HTTP 200 status code for the JS Project page
    @Test public void jsProjectHTTP200() {
        int statusCode = getStatusCode(jsProjectURL);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    //WT22: Testing home URL link
    @Test public void homeURL() {
        // Wait for the homeURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getHomeURL()));
        // Click on the homeURL element
        driver.findElement(jsProject.getHomeURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(homeURL, driver.getCurrentUrl());
    }
    
    //WT23: Testing projects URL link
    @Test public void projectsURL() {
        // Wait for the projectsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getProjectsURL()));
        // Click on the projectsURL element
        driver.findElement(jsProject.getProjectsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(projectsURL, driver.getCurrentUrl());
    }

    //WT24: Testing interests URL link
    @Test public void interestsURL() {
        // Wait for the interestsURL element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getInterestsURL()));
        // Click on the interestsURL element
        driver.findElement(jsProject.getInterestsURL()).click();
        // Verify that the current URL matches the expected URL
        Assert.assertEquals(interestsURL, driver.getCurrentUrl());
    }

    //WT25: Testing header after player x's turn
    @Test public void xTest() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        topLeft.click();
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player O turn"));
    }

    //WT26: Testing header after player o's turn
    @Test public void oTest() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x 
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        topLeft.click();
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topCenter);
        topCenter.click();
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player X turn"));
    }

    //WT27: Left vertical column player x win
    @Test public void leftVertical() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        jsExecutor.executeScript("arguments[0].click();", topLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topCenter);
        jsExecutor.executeScript("arguments[0].click();", topCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleLeft);
        jsExecutor.executeScript("arguments[0].click();", middleLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topRight);
        jsExecutor.executeScript("arguments[0].click();", topRight);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player X is the winner!"));
    }

    //WT28: Center vertical column player o win
    @Test public void centerVertical() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topRight);
        jsExecutor.executeScript("arguments[0].click();", topRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleRight);
        jsExecutor.executeScript("arguments[0].click();", middleRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topCenter);
        jsExecutor.executeScript("arguments[0].click();", topCenter);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player O is the winner!"));
    }

    //WT29: Right vertical column player x win
    @Test public void rightVertical() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topRight);
        jsExecutor.executeScript("arguments[0].click();", topRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleRight);
        jsExecutor.executeScript("arguments[0].click();", middleRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomRight);
        jsExecutor.executeScript("arguments[0].click();", bottomRight);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player X is the winner!"));
    }

    //WT30: Top horizontal row player o win
    @Test public void topHorizontal() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topRight);
        jsExecutor.executeScript("arguments[0].click();", topRight);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topCenter);
        jsExecutor.executeScript("arguments[0].click();", topCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        jsExecutor.executeScript("arguments[0].click();", topLeft);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player O is the winner!"));
    }

    //WT31: Middle horizontal row player x win
    @Test public void middleHorizontal() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleRight);
        jsExecutor.executeScript("arguments[0].click();", middleRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleLeft);
        jsExecutor.executeScript("arguments[0].click();", middleLeft);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player X is the winner!"));
    }   

    //WT32: Bottom horizontal row player o win
    @Test public void bottomHorizontal() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleRight);
        jsExecutor.executeScript("arguments[0].click();", middleRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topCenter);
        jsExecutor.executeScript("arguments[0].click();", topCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomRight);
        jsExecutor.executeScript("arguments[0].click();", bottomRight);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player O is the winner!"));
    } 

    //WT33: Top left to bottom right diagonal player x win
    @Test public void topLeftToBottomRightDiagonal() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomCenter);
        jsExecutor.executeScript("arguments[0].click();", bottomCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        jsExecutor.executeScript("arguments[0].click();", topLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomRight);
        jsExecutor.executeScript("arguments[0].click();", bottomRight);
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player X is the winner!"));
    }    

    //WT34: Top right to bottom left diagonal player o win
    @Test public void topRightToBottomLeftDiagonal() {
        wait.until(ExpectedConditions.elementToBeClickable(jsProject.getTopLeft()));
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleRight);
        jsExecutor.executeScript("arguments[0].click();", middleRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", middleCenter);
        jsExecutor.executeScript("arguments[0].click();", middleCenter);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topLeft);
        jsExecutor.executeScript("arguments[0].click();", topLeft);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomLeft);
        jsExecutor.executeScript("arguments[0].click();", bottomLeft);
        // Player x turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", bottomRight);
        jsExecutor.executeScript("arguments[0].click();", bottomRight);
        // Player o turn
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", topRight);
        jsExecutor.executeScript("arguments[0].click();", topRight);        
        Assert.assertTrue(driver.findElement(jsProject.getHeader()).getText().contains("Player O is the winner!"));
    }    

    //WT35: Repeated press of buttons

    //WT36: Repeated press header test

    //WT37: 

}