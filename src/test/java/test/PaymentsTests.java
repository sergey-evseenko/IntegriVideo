package test;

import models.Card;
import models.User;
import org.testng.annotations.Test;

public class PaymentsTests extends BaseTest {
    User user = new User("sergey.evseenko@mailinator.com", "Alfie_07061989");
    //add card
    //verify
    //remove card
    //verify

    @Test
    public void addAndRemoveCard() {
        Card card = new Card("4242 4242 4242 4242", "01", "2022", "Sergey Evseenko");
        loginPage
                .openPage()
                .login(user);
        billingPage
                .openPage()
                .addCard(card)
                .verifyAddedCard("424242******4242 (Visa)")
                .removeCard(1)
                .verifyThatNumberOfCardsDecreased();
    }

}
