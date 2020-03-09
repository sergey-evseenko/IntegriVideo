package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.ChatPage;
import pages.FileUploadModal;
import pages.SettingsModal;
import pages.SignUp;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    ChatPage chatPage;
    SettingsModal settingsModal;
    FileUploadModal fileUploadModal;
    SignUp signUpPage;
    private WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        chatPage = new ChatPage(driver);
        settingsModal = new SettingsModal(driver);
        fileUploadModal = new FileUploadModal(driver);
        signUpPage = new SignUp(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
