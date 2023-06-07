package pages;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.chrome.ChromeDriver;


public class HomePage {
    WebDriver driver = new ChromeDriver();
    By navBar = By.xpath("//*[@class='navbar-toggler']");
    By homeLink;
    By projectsLink;
    By interestsLink;
    By backgroundImage = By.id("bgImage");
    By profilePhoto = By.id("profilePicture");
    By homeName = By.id("homeName");
    By homeBlurb = By.id("homeBlurb");
    By resume = By.id("resume");
    By linkedInButton = By.id("linkedinButton");
    By githubButton = By.id("githubButton");
    By emailButton = By.id("emailButton");

    public void Home(WebDriver driver) {
        this.driver = driver;
    }

    // return PDF page count
    public static int getPageCount(PDDocument doc) {
		int pageCount = doc.getNumberOfPages();
		return pageCount;
	}

    public By getResume() {
        return resume;
    }

    public By getBackgroundImage() {
        return backgroundImage;
    }

    public By getProfilePhoto() {
        return profilePhoto;
    }

    public By getNavBarButton() {
        return navBar;
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

    public String getHomeBlurb() {
        return driver.findElement(homeBlurb).getText();
    }

    public String getHomeName() {
        return driver.findElement(homeName).getText();
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