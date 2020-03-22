package pages;

import models.Card;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BillingPage extends BasePage {
    int numberOfCardsBefore, numberOfCardsAfter;

    @FindBy(name = "number")
    WebElement number;
    @FindBy(name = "expirationMonth")
    WebElement expirationMonth;
    @FindBy(name = "expirationYear")
    WebElement expirationYear;
    @FindBy(name = "cardholderName")
    WebElement cardholderName;


    By cardsLocator = By.cssSelector(".col-md-7");

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
        numberOfCardsBefore = getNumberOfCards();
        driver.findElement(By.linkText("Add new")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".paypal-button")));
        number.sendKeys(card.getCardNumber());
        expirationMonth.sendKeys(card.getMonth());
        expirationYear.sendKeys(card.getYear());
        cardholderName.sendKeys(card.getName());
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn"));
        wait.until(ExpectedConditions.elementToBeClickable(buttons.get(4)));
        buttons.get(4).click();
        return this;
    }

    public BillingPage verifyAddedCard(String expectedText) {
        List<WebElement> cards = driver.findElements(cardsLocator);
        numberOfCardsAfter = getNumberOfCards();
        assertEquals(numberOfCardsAfter, numberOfCardsBefore + 1, "Adding card Error!");
        String actualText = cards.get(numberOfCardsAfter - 1).getText();
        assertEquals(actualText, expectedText, "c");
        return this;
    }

    public BillingPage removeCard(int index) {
        numberOfCardsBefore = getNumberOfCards();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector(".col-md-2"));
        removeButtons.get(index - 1).click();
        return this;
    }

    public BillingPage verifyThatNumberOfCardsDecreased() {
        numberOfCardsAfter = getNumberOfCards();
        assertEquals(numberOfCardsAfter, numberOfCardsBefore - 1, "Removing card Error!");
        return this;
    }

    public int getNumberOfCards() {
        List<WebElement> cards = driver.findElements(cardsLocator);
        return cards.size();
    }
}
