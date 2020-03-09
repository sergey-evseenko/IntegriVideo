package tests;

import org.testng.annotations.Test;

public class FunctionalTests extends BaseTest {


    @Test
    public void siugnUpVerification() {

        signUpPage.openPage("https://dev.integrivideo.com/signup");
        signUpPage.providingDataAndClicking("test5@gmail.com", "Alfie_07061999");
        signUpPage.messageVerification("Message with instructions was sent");

    }


}
