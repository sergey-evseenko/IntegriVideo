package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class SettingsModal extends BasePage {
    public SettingsModal(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://dev.integrivideo.com/demo/chat/new");
    }

    public void changeName(String name) {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userName")).clear();
        driver.findElement(By.name("userName")).sendKeys(name);
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
    }

    public void verifyName(String name) {
        String actualUserName = driver.findElement(By.cssSelector(".integri-session-user-name")).getText();
        assertEquals(actualUserName, name, "Updating Name Error!");
    }

    public void changeEmail(String email) {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userEmail")).clear();
        driver.findElement(By.name("userEmail")).sendKeys(email);
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
    }

    public void verifyEmail(String email) {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        String updatedEmail = driver.findElement(By.name("userEmail")).getAttribute("value");
        assertEquals(updatedEmail, email, "Updating Email Error!");

    }

    public void changePhotUrl(String url) {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userPic")).clear();
        driver.findElement(By.name("userPic")).sendKeys(url);
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
    }

    public void verifyPhotUrl(String url) {
        String updatedUrl = driver.findElement(By.cssSelector(".integri-user-pic")).getAttribute("style");
        assertEquals(updatedUrl, "background-image: url(\"https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png\");", "Updating logo Error!");
    }
}
