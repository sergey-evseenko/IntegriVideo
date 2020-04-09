package test;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class ChatTests extends BaseTest {

    String message = "AutoTests";

    @Test(description = "Send one message.")
    @Link("https://app-stage.playershealthclaims.com/")
    @Issue("PHC-2576")
    @TmsLink("PHC-2576")
    public void sendMessage() {
        chatPage.openPage();
        chatPage.writeText("Test");
        chatPage.clickSend();
        chatPage.messageShouldExist(0, "Test");
    }

    @Test(description = "Verify 'Invite' link.")
    public void verifyInviteLink() {
        chatPage.openPage();
        chatPage.clickInviteButton();
        chatPage.verifyBufferLink();
    }

    @Test
    public void sendMessageByEnter() throws InterruptedException {
        chatPage.openPage();
        chatPage.sedMessageByEnter(message);
        Thread.sleep(1000);
        chatPage.verifyMessage(message);
    }

    @Test
    public void sendMessageByButton() {
        chatPage.openPage();
        chatPage.sendMessageByButton(message);
        chatPage.verifyMessage(message);
    }

    @Test
    @Ignore
    public void sendLongMessage() {
        String longMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at malesuada neque. Vivamus non tincidunt lectus. Donec vitae mauris maximus nisl maximus iaculis. Praesent vehicula magna nec justo volutpat viverra. Maecenas at iaculis turpis. Phasellus accumsan scelerisque nunc, id maximus enim luctus vitae. Pellentesque malesuada feugiat magna, venenatis dictum velit volutpat sodales. Curabitur facilisis condimentum consectetur. Praesent in magna eget justo porttitor auctor. Donec eu tincidunt arc\n" +
                "Aenean facilisis, magna sed tincidunt euismod, diam elit semper tortor, vel viverra metus orci quis mauris. Proin vel facilisis metus. Maecenas non urna nulla. Suspendisse ut tincidunt velit. Mauris pretium diam a lorem ultricies suscipit. Nullam vel varius purus. Morbi viverra efficitur dolor ac condimentum. Suspendisse potenti. Nunc non venenatis magna. In ultricies erat vel urna bibendum, dignissim dictum ipsum congue. Integer tortor orci, semper non ipsum et, maximus hendrerit sem0";
        chatPage.openPage();
        chatPage.sendMessageByButton(longMessage);
        chatPage.verifyMessage(longMessage);
    }

    @Test
    public void sendLink() {
        String link = "https://www.youtube.com/";
        chatPage.openPage();
        chatPage.sendMessageByButton(link);
        chatPage.verifyLink("www.youtube.com\n" +
                "YouTube\n" +
                "Enjoy the videos and music you love, upload original content, and share it all with friends, family, and the world on YouTube.");
    }

    @Test
    //@Ignore
    public void sendCode() {
        String code = "<html><body><p>test</p></body></html>";
        chatPage.openPage();
        chatPage.sendMessageByButton(code);
        chatPage.verifyMessage(code);
    }

    @Test
    public void editMessage() {
        String updatedmessage = "Updated ";
        chatPage.openPage();
        chatPage.sendMessageByButton(message);
        chatPage.editMessage(message, updatedmessage);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chatPage.verifyMessage(updatedmessage + message);
    }

    @Test
    public void editMessageWithRemove() {
        chatPage.openPage();
        chatPage.sendMessageByButton(message);
        chatPage.editMessageWithRemove(message);
        chatPage.verifyErrorMessage("Message cannot be empty!");
    }

    @Test
    public void deleteMessage() {
        chatPage.openPage();
        chatPage.sendMessageByButton(message);
        chatPage.deleteMessage(1);
        chatPage.verifyDeletedMessage("removed...");
    }

    @Test
    public void codeButton() {
        chatPage.openPage();
        chatPage.copyAndVerifyCode();
    }

    @Test
    //@Ignore
    public void messagesLimit() {
        chatPage.openPage();
        chatPage.sendMessagesAndVerifyLimit(11);
    }

/*
    @Test
    public void runTest() {
        System.out.println(System.getProperty("userName"));
    }

 */

}
