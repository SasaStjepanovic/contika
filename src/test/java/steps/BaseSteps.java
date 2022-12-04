package steps;

import excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.BasePage;
import pages.GeneralPage;
import pages.LoginPage;
import pages.UserManagementPage;
import pages.components.HeaderComponent;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class BaseSteps extends BaseTest {

    Map<String, String> data;

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");

    @Before
    public void setup() throws Exception {
        init(browser, wait);
        openSindriApp(env);
    }

    @After
    public void tearDown() throws IOException {
//        quit();
    }

    @Given("a user reads test data from {string} {string} by id {string}")
    public void aUserReadsTestDataFromById(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
    }

    @And("the landing-page is opened")
    public void theLandingPageIsOpened() {
        new BasePage(driver).checkUrlPage(data.get("urlBasePage"));
    }

    @And("user clicks login button")
    public void userClicksLoginButton() {
        new LoginPage(driver).clickLoginButton();
    }

    @When("user enters username and password")
    public void userEntersUsernameAndPassword() throws InterruptedException {
        new LoginPage(driver).signIn(data.get("userName"),data.get("password"));
    }

    @And("hover")
    public void hover() throws InterruptedException {
        new LoginPage(driver).hoverOverUserName();
    }

    @And("user clicks sigin button")
    public void userClicksSiginButton() {
        new LoginPage(driver).signinButton();
    }

    @Then("user should be verified login action")
    public void userShouldBeVerifiedLoginAction() throws InterruptedException {
        new LoginPage(driver).signinVerification(data.get("testType"),data.get("expectedText"), data.get("attributeType"));
    }

    @Then("user should be verify all menu items")
    public void userShouldBeVerifyAllMenuItems() throws InterruptedException {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.clickMenu();
        hp.checkMenuItems(new String[]{data.get("menuItems0"),data.get("menuItems1"),data.get("menuItems2")});
    }

    @And("user clicks signout button")
    public void userClicksSignoutButton() {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.clickProfil();
        hp.clickLogout();
    }

    @Then("user should be verify logout action")
    public void userShouldBeVerifyLogoutAction() {
        new LoginPage(driver).signoutVerification(data.get("loginText"));
    }

    @And("user clicks account settings button")
    public void userClicksAccountSettingsButton() throws InterruptedException {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.clickProfil();
        hp.clickAccount();
        Thread.sleep(2000);
    }

    @When("user changes first and last name")
    public void userChangesFirstAndLastName() throws InterruptedException {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.enterFirstAndLastName(data.get("randomTypeYesNo"),data.get("firstnameAS"),data.get("lastnameAS"));
    }

    @And("user clicks save button")
    public void userClicksSaveButton() {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.clickSaveButton();
    }

    @And("user clicks OK button in confirmation window")
    public void userClicksOKButtonInConfirmationWindow() {
        new GeneralPage(driver).clickConfirmOK();
        new HeaderComponent(driver).savedVerification(data.get("successfullySavedText"));
    }

    @Then("user should be verify random changed credentials")
    public void userShouldBeVerifyRandomChangedCredentials() {
        new HeaderComponent(driver).verifyCredentialsAfterRandomChanges(data.get("attributeType"));
    }

    @And("user closes Account setting window")
    public void userClosesAccountSettingWindow() {
        new HeaderComponent(driver).clickCloseButton();
    }

    @Then("user should be verify first and last name is empty and save button is disabled")
    public void userShouldBeVerifyFirstAndLastNameIsEmptyAndSaveButtonIsDisabled() {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.accountSettingsMessageVerification(data.get("accountFirstEmptyMessage"), data.get("accountLastEmptyMessage"));
        hp.isSaveButtonDisabled(data.get("saveButtonDisabled"), data.get("attributeType"));
    }

    @And("user clicks on menu and user management item")
    public void userClicksOnMenuAndUserManagementItem() {
        new UserManagementPage(driver).openUserManagementPage();
    }

    @And("user verify that user management page is opened")
    public void userVerifyThatUserManagementPageIsOpened() {
        new BasePage(driver).checkUrlPage(data.get("urlBasePage"));

    }

    @Then("user should be verify not random changed credentials")
    public void userShouldBeVerifyNotRandomChangedCredentials() {
        new HeaderComponent(driver).verifyCredentialsAfterNotRandomChanges(data.get("attributeType"),data.get("firstnameAS"),data.get("lastnameAS"));
    }

    @When("user deletes first technician")
    public void userDeletesFirstTechnician() {
        UserManagementPage um = new UserManagementPage(driver);
        um.deleteFirstTechnician();
    }

    @When("user creates technician with random data")
    public void userCreatesTechnicianWithRandomData() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.addTechnician();
        um.enterTehnicianData(data,data.get("randomTypeYesNo"),data.get("firstNameTechnician"),data.get("lastNameTechnician"),data.get("emailTechnician"));
        um.closeTechnician();
    }

    @Then("user should verify that all technicians are deleted")
    public void userShouldVerifyThatAllTechniciansAreDeleted() {
        UserManagementPage um = new UserManagementPage(driver);
        um.deleteAllTechnicians();
    }
}