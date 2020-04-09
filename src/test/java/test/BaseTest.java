package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import steps.LoginSteps;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    ChatPage chatPage;
    SettingsModal settingsModal;
    FileUploadModal fileUploadModal;
    SignUpPage signUpPage;
    LoginPage loginPage;
    ProjectPage projectPage;
    BillingPage billingPage;
    LoginSteps loginSteps;
    private WebDriver driver;

    @BeforeMethod(description = "Opening chrome driver...")
    public void setDriver(ITestContext context) {
        /*System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();*/
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        chatPage = new ChatPage(driver);
        settingsModal = new SettingsModal(driver);
        fileUploadModal = new FileUploadModal(driver);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        billingPage = new BillingPage(driver);
        loginSteps = new LoginSteps(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
