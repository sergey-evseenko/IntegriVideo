package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUp extends BasePage {
    public SignUp(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void providingDataAndClicking(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector(".btn-primary")).click();

    }

    public void messageVerification(String expectedMessage) {

        driver.findElement(By.xpath("//*[text()='" + expectedMessage + "']")).getText();
    }


}
