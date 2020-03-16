package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public BasePage isPageOpened() {
        return null;
    }

    public ChatPage openPage() {
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        return this;
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

    public void inviteButtonClick() {
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
    }

    public void verifyDeletedMessage(String text) {
        driver.findElement(By.xpath("//*[text()='" + text + "']"));
    }

    public void copyAndVerifyCode() {

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

    public void sendMessagesAndVerifyLimit() {

        for (int i = 0; i < 11; i++) {
            driver.findElement(By.cssSelector("textarea[placeholder='Start typing here']")).sendKeys("text");
            driver.findElement(By.cssSelector(".integri-chat-send-message")).click();
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".integri-chat-message-text"), i + 1));
        }
        driver.findElement(By.cssSelector(".integri-button big-button"));
    }

    public void verifyLink(String linkText) {
        String actualLinkText = driver.findElement(By.cssSelector(".integri-chat-message-attachment")).getText();
        assertEquals(actualLinkText, linkText, "Invalid link preview!");
    }


}
