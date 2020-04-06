package test;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import models.Project;
import models.User;
import org.testng.annotations.Test;

import java.util.UUID;

public class ProjectsTests extends BaseTest {
    User user = new User("sergey.evseenko@mailinator.com", "Alfie_07061989");


    @Test(priority = 100)
    public void signUp() {
        User user = new User(UUID.randomUUID().toString() + "@gmail.com", "Alfie_2244");
        signUpPage
                .openPage()
                .signUp(user)
                .verifyMessage("Message with instructions was sent");
    }

    @Test(priority = 99, description = "Creation of project")
    @Description("Создаем проект и проверяем, что он создался корректно!")
    @Link("https://app.stage.flowhealthlabs.com/")
    @Issue("FS-1898")
    @TmsLink("FS-1899")
    public void createProject() {
        Project project = new Project(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString() + ".com");
        loginSteps.login(user);
        projectPage
                .creteProject(project)
                .verifyThatNumberOfProjectsIncreased();
    }

    @Test(priority = 98)
    public void editProject() {
        Project project = new Project(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString() + ".com");
        loginPage
                .openPage()
                .login(user);
        projectPage
                .editProject(project)
                .verifyUpdatedProject(project);
    }

    @Test(priority = 97)
    public void addAndRemoveDomain() {
        String domainName = "domain.com";
        loginPage
                .openPage()
                .login(user);
        projectPage
                .addDomain(domainName)
                .verifyaddedDomain(domainName)
                .removeDomain()
                .verifyRemovedDomain();
    }

    @Test(priority = 96)
    public void addAndEditComponent() {
        loginPage
                .openPage()
                .login(user);
        projectPage
                .createComponent("My component")
                .verifyCodeAndButtonTitle()
                .clickUpdateAndVerifyThatNumberOfComponentsIncreased();
    }

}
