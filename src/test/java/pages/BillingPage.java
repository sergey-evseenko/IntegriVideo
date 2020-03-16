package pages;

import models.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BillingPage extends BasePage {
    int numberOfCardsBefore, numberOfCardsAfter;

    public BillingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public BillingPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Add new")));
        return this;
    }

    public BillingPage openPage() {
        driver.get("https://dev.integrivideo.com/app/billing");
        isPageOpened();
        return this;
    }

    public BillingPage addCard(Card card) {
        List<WebElement> cards = driver.findElements(By.cssSelector(".col-md-7"));
        numberOfCardsBefore = cards.size();
        driver.findElement(By.linkText("Add new")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".paypal-button")));
        driver.findElement(By.name("number")).sendKeys(card.getCardNumber());
        driver.findElement(By.name("expirationMonth")).sendKeys(card.getMonth());
        driver.findElement(By.name("expirationYear")).sendKeys(card.getYear());
        driver.findElement(By.name("cardholderName")).sendKeys(card.getName());
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn"));
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(4)));
        buttons.get(4).click();
        return this;
    }

    public BillingPage verifyAddedCard(String expectedText) {
        List<WebElement> cards = driver.findElements(By.cssSelector(".col-md-7"));
        numberOfCardsAfter = cards.size();
        assertEquals(numberOfCardsAfter, numberOfCardsBefore + 1, "Adding card Error!");
        String actualText = cards.get(numberOfCardsAfter - 1).getText();
        assertEquals(actualText, expectedText, "Adding card Error!");
        return this;
    }

    public BillingPage removeCard(int index) {
        List<WebElement> cards = driver.findElements(By.cssSelector(".col-md-7"));
        numberOfCardsBefore = cards.size();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".col-md-2"));
        removeButtons.get(index - 1).click();
        return this;
    }

    public BillingPage verifyTahtNumberOfCardsDecreased() {
        List<WebElement> cards = driver.findElements(By.cssSelector(".col-md-7"));
        numberOfCardsAfter = cards.size();
        assertEquals(numberOfCardsAfter, numberOfCardsBefore - 1, "Adding card Error!");
        return this;
    }
}
