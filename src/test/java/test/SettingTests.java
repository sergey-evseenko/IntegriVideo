package test;

import org.testng.annotations.Test;

public class SettingTests extends BaseTest {

    @Test
    public void changeName() {
        chatPage.openPage();
        settingsModal.changeName("UserName");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        settingsModal.verifyName("UserName");
    }

    @Test
    public void changeEmail() {
        settingsModal.openPage();
        settingsModal.changeEmail("testemail@gmail.com");
        settingsModal.verifyEmail("testemail@gmail.com");
    }

    @Test
    public void changePhotoUrl() {
        settingsModal.openPage();
        settingsModal.changePhotUrl("https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        settingsModal.verifyPhotUrl("https://upload.wikimedia.org/wikipedia/commons/8/87/Google_Chrome_icon_%282011%29.png");
    }
}
