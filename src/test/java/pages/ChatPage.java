package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ChatPage extends BasePage {
    private final static By CHAT_INPUT = By.cssSelector("textarea");

    public ChatPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://dev.integrivideo.com/demo/chat/new");
    }

    public void writeText(String text) {
        driver.findElement(CHAT_INPUT).sendKeys(text);
    }

    public void clickSend() {
        driver.findElement(By.cssSelector(".integri-chat-send-message")).click();
    }

    public void messageShouldExist(int messageIndex, String text) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> message = driver.findElements(By.cssSelector(".integri-chat-message-text"));
        boolean isExist = message.get(messageIndex).getText().equals(text);
        assertTrue(isExist, "Message do not exist");

    }

    public void inviteClick() {
        driver.findElement(By.id("invite-users-to-chat")).click();
    }

    public void verifyBufferLink() {
        String currentLink = driver.getCurrentUrl();
        String copyLink = null;
        try {
            copyLink = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(currentLink, copyLink, "Link was copied incorrectly");

    }

    public void fileUpload(String url) {
        driver.findElement(By.cssSelector(".integri-chat-manual-upload")).click();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(System.getProperty("user.dir") + url);
        driver.findElement(By.cssSelector(".integri-file-upload-start")).click();

    }

    public void verifyFile(String validName) {
        String fileName = driver.findElement(By.cssSelector(".integri-chat-message-attachment-file-name")).getText();
        assertEquals(fileName, validName, "File upload error");
    }

    public void sedMessageByEnter(String message) {
        driver.findElement(By.cssSelector("textarea[placeholder='Start typing here']")).sendKeys(message + Keys.ENTER);

    }

    public void verifyMessage(String expectedMessage) {
        String actualMessage = driver.findElement(By.cssSelector(".integri-chat-message-text")).getText();
        assertEquals(actualMessage, expectedMessage, "Sending message Error!");

    }

    public void sendMessageByButton(String message) {
        driver.findElement(By.cssSelector("textarea[placeholder='Start typing here']")).sendKeys(message);
        driver.findElement(By.cssSelector(".integri-chat-send-message")).click();

    }

    public void editMessage(String currentMessage, String updatedMessage) {
        driver.findElement(By.cssSelector(".iv-icon.iv-icon-pencil.integri-chat-edit-message")).click();
        driver.findElement(By.xpath("//textarea[text()='" + currentMessage + "']")).sendKeys(updatedMessage + Keys.ENTER);

    }

    public void editMessageWithRemove(String currentMessage) {
        driver.findElement(By.cssSelector(".iv-icon.iv-icon-pencil.integri-chat-edit-message")).click();
        driver.findElement(By.xpath("//textarea[text()='" + currentMessage + "']")).clear();
        driver.findElement(By.xpath("//textarea[text()='" + currentMessage + "']")).sendKeys(Keys.ENTER);

    }

    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = driver.findElement(By.cssSelector(".integri-notify.integri-notify-error")).getText();
        assertEquals(actualMessage, expectedMessage, "Incorrect validation message!");

    }

    public void deleteMessage() {

        driver.findElement(By.cssSelector(".iv-icon.iv-icon-trash2.integri-chat-remove-message")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//*[text()='removed...']"));
    }

    public void changeName() {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userName")).clear();
        driver.findElement(By.name("userName")).sendKeys("TestUser");
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actualUserName = driver.findElement(By.cssSelector(".integri-session-user-name")).getText();
        assertEquals(actualUserName, "TestUser", "Updating Name Error!");

    }

    public void changeEmail() {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userEmail")).clear();
        driver.findElement(By.name("userEmail")).sendKeys("testemail@gmail.com");
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        String updatedEmail = driver.findElement(By.name("userEmail")).getAttribute("value");
        assertEquals(updatedEmail, "testemail@gmail.com", "Updating Email Error!");


    }

    public void changePhotUrl() {
        driver.findElement(By.cssSelector(".integri-chat-settings")).click();
        driver.findElement(By.name("userPic")).clear();
        driver.findElement(By.name("userPic")).sendKeys("https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png");
        driver.findElement(By.cssSelector(".integri-user-settings-save")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String updatedUrl = driver.findElement(By.cssSelector(".integri-user-pic")).getAttribute("style");
        assertEquals(updatedUrl, "background-image: url(\"https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png\");", "Updating logo Error!");


    }

    public void copyCode() {

        driver.findElement(By.cssSelector(".component-code")).click();
        String currentCode = driver.findElement(By.cssSelector(".component-code")).getText();
        String copyCode = null;
        try {
            copyCode = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void checkLimit() {

        for (int i = 0; i < 12; i++) {
            driver.findElement(By.cssSelector("textarea[placeholder='Start typing here']")).sendKeys("text");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector(".integri-chat-send-message")).click();
        }
        driver.findElement(By.cssSelector(".integri-button big-button"));

    }


}
