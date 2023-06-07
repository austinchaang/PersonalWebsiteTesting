package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JSProject {
    WebDriver driver = new ChromeDriver();
    By homeLink;
    By projectsLink;
    By interestsLink;
    By topLeft = By.className("topleft");
    By topCenter = By.className("topcenter");
    By topRight = By.className("topright");
    By middleLeft = By.className("middleleft");
    By middleCenter = By.className("middlecenter");
    By middleRight = By.className("middleright");
    By bottomLeft = By.className("bottomleft");
    By bottomCenter = By.className("bottomcenter");
    By bottomRight = By.className("bottomright");
    By header = By.className("display-4");

    public void JSProject(WebDriver driver) {
        this.driver = driver;
    }

    public By getHomeURL() {
        homeLink = By.id("homeURL");
        return homeLink;
    }

    public By getProjectsURL() {
        projectsLink = By.id("projectsURL");
        return projectsLink;
    }

    public By getInterestsURL() {
        interestsLink = By.id("interestsURL");
        return interestsLink;
    }

    public By getTopLeft() {
        return topLeft;
    }
    
    public By getTopCenter() {
        return topCenter;
    }

    public By getTopRight() {
        return topRight;
    }

    public By getMiddleLeft() {
        return middleLeft;
    }

    public By getMiddleCenter() {
        return middleCenter;
    }

    public By getMiddleRight() {
        return middleRight;
    }

    public By getBottomLeft() {
        return bottomLeft;
    }

    public By getBottomCenter() {
        return bottomCenter;
    }

    public By getBottomRight() {
        return bottomRight;
    }

    public By getHeader() {
        return header;
    }
}