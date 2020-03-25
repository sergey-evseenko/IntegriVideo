import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class IntegriVideo {

    @Test
    public void findLocators() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://dev.integrivideo.com/demo/5e5bee9a9884e44e06570457");

        //1. Button "Invite user to chat"
        webDriver.findElement(By.id("invite-users-to-chat"));
        //2. Component code
        webDriver.findElement(By.className("component-code"));
        //3. Send message
        webDriver.findElement(By.className("integri-chat-send-message"));
        //4. Start chat
        webDriver.findElement(By.className("integri-chat-start-video"));
        //5. Settings
        webDriver.findElement(By.className("integri-chat-settings"));
        //6. Upload
        webDriver.findElement(By.className("integri-chat-manual-upload"));
        //7. Start typing here
        webDriver.findElement(By.xpath("//textarea[@placeholder='Start typing here']"));
        //8. Provided by
        webDriver.findElement(By.cssSelector("a[target=_blank]"));
        //9. Локаторы с модаки settings
        webDriver.findElement(By.name("userName"));
        webDriver.findElement(By.name("userEmail"));
        webDriver.findElement(By.name("userPic"));
        webDriver.findElement(By.className("integri-user-settings-save"));
        webDriver.findElement(By.className("integri-user-settings-reset"));
        List<WebElement> cross = webDriver.findElements(By.cssSelector(".iv-icon.iv-icon-cross.close-integri-modal"));
        cross.get(1);
        //10. Локаторы с модаки ‘Drag & drop to upload’
        webDriver.findElement(By.className("integri-file-upload-start"));
        webDriver.findElement(By.className("integri-file-upload-cancel"));
        webDriver.findElement(By.className("integri-file-upload-head-message"));
        webDriver.findElement(By.className("integri-file-upload-manual-init"));
        cross.get(0);
        //11. Локаторы, которые находятся на самом сообщении
        webDriver.findElements(By.className("integri-chat-message-container"));
        webDriver.findElements(By.className("integri-chat-edit-message"));
        webDriver.findElements(By.className("integri-chat-remove-message"));
        webDriver.findElements(By.className("integri-chat-message-text"));


    }

}
