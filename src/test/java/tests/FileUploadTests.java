package tests;

import org.testng.annotations.Test;

import java.io.File;

public class FileUploadTests extends BaseTest {

    @Test
    public void verifyFileUpload() {
        fileUploadModal.openPage();
        fileUploadModal.fileUpload(File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "my_file.pdf");
        fileUploadModal.verifyFile("my_file.pdf (29kb)");
    }

    @Test
    public void verifyFilesUpload() {
        fileUploadModal.openPage();
        fileUploadModal.fileUpload(File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "my_file.pdf");
        fileUploadModal.verifyFile("my_file.pdf (29kb)");
    }
}
