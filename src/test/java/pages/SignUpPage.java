package pages;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {

    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(css = ".btn-primary")
    WebElement signUpButton;


    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignUpPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        return this;
    }

    public SignUpPage openPage() {
        driver.get("https://dev.integrivideo.com/signup");
        isPageOpened();
        return this;
    }

    public SignUpPage signUp(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        signUpButton.click();
        return this;
    }

    public SignUpPage verifyMessage(String expectedMessage) {
        driver.findElement(By.xpath("//*[text()='" + expectedMessage + "']")).getText();
        return this;
    }

}
