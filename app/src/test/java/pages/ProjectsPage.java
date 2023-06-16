package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProjectsPage {
    WebDriver driver = new ChromeDriver();
    By homeLink;
    By projectsLink;
    By interestsLink;
    By websiteV1Link;
    By websiteV1GitHub;
    By jsProjectLink;
    By jsProjectGitHub;
    By websiteTestingGitHubLink;
    By linkedInButton = By.id("linkedinButton");
    By githubButton = By.id("githubButton");
    By emailButton = By.id("emailButton");

    public void Projects(WebDriver driver) {
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

    public By getWebsiteV1Link() {
        websiteV1Link = By.id("websiteV1Link");
        return websiteV1Link;
    }

    public By getWebsiteGitHubLink() {
        websiteV1GitHub = By.id("websiteV1GitHubLink");
        return websiteV1GitHub;
    }

    public By getWebsiteTestingGitHubLink() {
        websiteTestingGitHubLink = By.id("personalWebsiteTestingGitHubLink");
        return websiteTestingGitHubLink;
    }

    public By getjsProjectLink() {
        jsProjectLink = By.id("jsProjectLink");
        return jsProjectLink;
    }

    public By getjsProjectGitHubLink() {
        jsProjectGitHub = By.id("jsProjectGitHubLink");
        return jsProjectGitHub;
    }

    public By getLinkedInButton() {
        return linkedInButton;
    }

    public By getGitHubButton() {
        return githubButton;
    }

    public By getEmailButton() {
        return emailButton;
    }
}