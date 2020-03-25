package pages;

import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;


public class ProjectPage extends BasePage {

    @FindBy(linkText = "Edit")
    WebElement editButton;

    @FindBy(name = "name")
    WebElement projectName;

    @FindBy(css = "textarea")
    WebElement projectDescription;

    @FindBy(name = "domains[]")
    WebElement projectDomains;

    By domainsLocator = By.name("domains[]");


    int numberOfElementsBefore, numberOfElementsAfter;
    By addProject = By.cssSelector(".iv-icon-file-add");

    public ProjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addProject));
        return this;
    }

    public BasePage openPage() {
        return null;
    }

    public ProjectPage creteProject(Project project) {
        numberOfElementsBefore = driver.findElements(By.cssSelector(".project")).size();
        driver.findElement(By.xpath("//*[text()='Add project']")).click();
        projectName.sendKeys(project.getName());
        projectDescription.sendKeys(project.getDescription());
        projectDomains.sendKeys(project.getDomain());
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn"));
        buttons.get(4).click();
        return this;
    }

    public ProjectPage verifyThatNumberOfProjectsIncreased() {
        numberOfElementsAfter = driver.findElements(By.cssSelector(".project")).size();
        assertEquals(numberOfElementsAfter, numberOfElementsBefore + 1, "Project creation error");
        return this;
    }

    public void openProject(int index) {
        List<WebElement> projects = driver.findElements(By.cssSelector(".project"));
        projects.get(index).click();
    }

    public void openProjectAndClickEdit(int index) {
        openProject(index);
        editButton.click();
    }

    public void clickUpdateProject() {
        driver.findElement(By.xpath("//*[text()='Update']")).click();
    }


    public ProjectPage editProject(Project project) {
        openProjectAndClickEdit(1);
        projectName.clear();
        projectName.sendKeys(project.getName());
        projectDescription.clear();
        projectDescription.sendKeys(project.getDescription());
        projectDomains.clear();
        projectDomains.sendKeys(project.getDomain());
        clickUpdateProject();
        return this;
    }

    public ProjectPage verifyUpdatedProject(Project project) {
        openProjectAndClickEdit(1);
        String actualName = projectName.getAttribute("value");
        String actualDescription = projectDescription.getAttribute("value");
        String actualDomain = projectDomains.getAttribute("value");
        assertEquals(actualName, project.getName(), "Updating Name Error!");
        assertEquals(actualDescription, project.getDescription(), "Updating description Error!");
        assertEquals(actualDomain, project.getDomain(), "Updating domain Error!");
        return this;
    }

    public ProjectPage addDomain(String domainName) {
        openProjectAndClickEdit(1);
        List<WebElement> domains = driver.findElements(domainsLocator);
        domains.get(domains.size() - 1).sendKeys(domainName);
        clickUpdateProject();
        return this;
    }

    public ProjectPage verifyaddedDomain(String expectedDomainName) {
        openProjectAndClickEdit(1);
        List<WebElement> domains = driver.findElements(domainsLocator);
        String actualDomainName = domains.get(domains.size() - 2).getAttribute("value");
        assertEquals(actualDomainName, expectedDomainName, "Adding domain Error!");
        return this;
    }

    public ProjectPage removeDomain() {
        List<WebElement> domains = driver.findElements(domainsLocator);
        numberOfElementsBefore = domains.size();
        List<WebElement> removeButton = driver.findElements(By.cssSelector(".input-group-text"));
        removeButton.get(removeButton.size() - 2).click();
        clickUpdateProject();
        return this;
    }

    public ProjectPage verifyRemovedDomain() {
        openProjectAndClickEdit(1);
        List<WebElement> domains = driver.findElements(domainsLocator);
        numberOfElementsAfter = domains.size();
        assertEquals(numberOfElementsAfter, numberOfElementsBefore - 1, "Removing domain Error!");
        return this;
    }

    public ProjectPage createComponent(String componentName) {
        openProject(1);
        List<WebElement> components = driver.findElements(By.cssSelector(".component"));
        numberOfElementsBefore = components.size();
        components.get(components.size() - 1).click();
        projectName.sendKeys(componentName);
        List<WebElement> buttons1 = driver.findElements(By.cssSelector(".btn"));
        buttons1.get(4).click();
        return this;
    }

    public ProjectPage verifyCodeAndButtonTitle() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".component-code")));
        List<WebElement> buttons2 = driver.findElements(By.cssSelector(".btn"));
        assertEquals(buttons2.get(4).getText(), "Update", "Invalid button name");
        return this;
    }

    public ProjectPage clickUpdateAndVerifyThatNumberOfComponentsIncreased() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn"));
        buttons.get(4).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".component"), numberOfElementsBefore + 1));
        return this;
    }


}
