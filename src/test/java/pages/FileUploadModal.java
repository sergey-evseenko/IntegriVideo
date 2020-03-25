package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class FileUploadModal extends BasePage {
    public FileUploadModal(WebDriver driver) {
        super(driver);
    }

    public BasePage isPageOpened() {
        return null;
    }

    public FileUploadModal openPage() {
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        return this;
    }

    public void fileUpload(String url) {
        driver.findElement(By.cssSelector(".integri-chat-manual-upload")).click();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(System.getProperty("user.dir") + url);
        driver.findElement(By.cssSelector(".integri-file-upload-start")).click();
    }

    public void verifyFile(String validName) {
        String fileName = driver.findElement(By.cssSelector(".integri-chat-message-attachment-file-name")).getText();
        assertEquals(fileName, validName, "Sergey Evseenko Error!");
    }
}
