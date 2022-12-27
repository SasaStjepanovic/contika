package steps;

import excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;
import pages.*;
import pages.components.HeaderComponent;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

public class BaseSteps extends BaseTest {

    Map<String, String> data;

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");

    UserManagementPage um = new UserManagementPage(driver);

    String randomFirstNameTechnician;
    String randomLastNameTechnician;
    String randomEmailTechnician;
    HeaderComponent hc = new HeaderComponent(driver);
    String randomFirstName;
    String randomLastName;

    CheckListManagementPage cl = new CheckListManagementPage(driver);

    String randomCheckListName;
    String randomHeaderName;
    String randomItemName;

    @Before
    public void setup() throws Exception {
        init(browser, wait);
        openSindriApp(env);
        randomFirstName = hc.randomFirstName();
        randomLastName = hc.randomLastName();

        randomFirstNameTechnician = um.randomTechnicianFirstName();
        randomLastNameTechnician = um.randomTechnicianLastName();
        randomEmailTechnician = um.randomTechnicianEmail();

        randomCheckListName = cl.randomCheckListName();
        randomHeaderName = cl.randomHeaderName();
        randomItemName = cl.randomItemName();
    }

    @After
    public void tearDown() throws IOException {
        quit();
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
        new LoginPage(driver).signIn(data.get("userName"), data.get("password"));
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
        new LoginPage(driver).signinVerification(data.get("testType"), data.get("expectedText"), data.get("attributeType"));
    }

    @Then("user should be verify all menu items")
    public void userShouldBeVerifyAllMenuItems() throws InterruptedException {
        HeaderComponent hp = new HeaderComponent(driver);
        hp.clickMenu();
        hp.checkMenuItems(new String[]{data.get("menuItems0"), data.get("menuItems1"), data.get("menuItems2")});
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
        hp.enterFirstAndLastName(data.get("randomTypeYesNo"), data.get("firstnameAS"), data.get("lastnameAS"), randomFirstName, randomLastName);
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
        new HeaderComponent(driver).verifyCredentialsAfterRandomChanges(data.get("attributeType"), randomFirstName, randomLastName);
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
        new HeaderComponent(driver).verifyCredentialsAfterNotRandomChanges(data.get("attributeType"), data.get("firstnameAS"), data.get("lastnameAS"));
    }

    @When("user deletes first technician")
    public void userDeletesFirstTechnician() {
        UserManagementPage um = new UserManagementPage(driver);
        um.deleteFirstTechnician();
    }

    @When("user creates technician with data")
    public void userCreatesTechnicianWithData() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.addTechnician();
        um.enterTehnicianData(data, data.get("randomTypeYesNo"), data.get("firstNameTechnician"), data.get("lastNameTechnician"), data.get("emailTechnician"));
        um.closeTechnician();
    }

    @And("user check row numbers")
    public void userCheckRowNumbers() {
        new GeneralPage(driver).rowsPerPage(data.get("rowNumber"));
    }

    @When("user creates two technicians with same data")
    public void userCreatesTwoTechniciansWithSameData() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.addTechnician();
        um.enterTehnicianData(data, data.get("randomTypeYesNo"), data.get("firstNameTechnician"), data.get("lastNameTechnician"), data.get("emailTechnician"));
        um.enterTehnicianData(data, data.get("randomTypeYesNo"), data.get("firstNameTechnician"), data.get("lastNameTechnician"), data.get("emailTechnician"));
    }

    @Then("user should verify that is not posible to create user with the same data")
    public void userShouldVerifyThatIsNotPosibleToCreateUserWithTheSameData() {
        UserManagementPage um = new UserManagementPage(driver);
        um.verifyUserAlreadyExists(data.get("userAlreadyExists"));
        um.closeTechnician();
    }

    @Then("user should verify that is not posible to create user with the invalid email address")
    public void userShouldVerifyThatIsNotPosibleToCreateUserWithTheInvalidEmailAddress() {
        UserManagementPage um = new UserManagementPage(driver);
        um.verifyInvalidEmail(data.get("invalidEmailMessage"));
    }

    @When("user creates technician with invalid email address")
    public void userCreatesTechnicianWithInvalidEmailAddress() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.addTechnician();
        um.enterTehnicianData(data, data.get("randomTypeYesNo"), data.get("firstNameTechnician"), data.get("lastNameTechnician"), data.get("emailTechnician"));
    }

    @When("user edit personal information of existing technician")
    public void userEditPersonalInformationOfExistingTechnician() {
        UserManagementPage um = new UserManagementPage(driver);
        um.editTechnician(data.get("randomTypeYesNo"), data.get("firstNameTechnician"), data.get("lastNameTechnician"), data.get("emailTechnician"), randomFirstNameTechnician, randomLastNameTechnician, randomEmailTechnician);
    }

    @When("user edit licence allocated of existing technician")
    public void userEditLicenceAllocatedOfExistingTechnician() {
        UserManagementPage um = new UserManagementPage(driver);
        um.licenceAllocated();
        um.closeEditTechnician();
    }

    @Then("user should verify that technician is edited")
    public void userShouldVerifyThatTechnicianIsEdited() {
        UserManagementPage um = new UserManagementPage(driver);
        um.clickEditButton();
        um.verifyEditedTechnicians(data.get("attributeType"), randomFirstNameTechnician, randomLastNameTechnician, randomEmailTechnician);
    }

    @Then("user should verify clickable of remove allocation button")
    public void userShouldVerifyClickableOfRemoveAllocationButton() {
        new UserManagementPage(driver).verifyRemoveAllocationButton(data.get("removeAllocationButtonText"));
    }

    @When("user delete all technicians")
    public void userDeleteAllTechnicians() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.deleteAllTechnicians(data.get("rowNumber"));
    }

    @Then("user should verify that all technicians are deleted")
    public void userShouldVerifyThatAllTechniciansAreDeleted() {
        new UserManagementPage(driver).verifyTechnicianListEmpty();
    }

    @When("user searches one of technicians")
    public void userSearchesOneOfTechnicians() {
        new UserManagementPage(driver).serachTehcnican(data.get("searchTechnician"));
    }

    @Then("user should verify searched technician")
    public void userShouldVerifySearchedTechnician() throws InterruptedException {
        UserManagementPage um = new UserManagementPage(driver);
        um.VerifySearchedText(data.get("searchedTechnician"));
        um.clearSearch(data.get("emptySearch"));
    }

    @Then("user should verify searched technician is not displayed")
    public void userShouldVerifySearchedTechnicianIsNotDisplayed() {
        UserManagementPage um = new UserManagementPage(driver);
        um.verifyTechnicianListEmpty();
        um.clearSearch(data.get("emptySearch"));
    }

    @And("user clicks on menu and checklist management item")
    public void userClicksOnMenuAndChecklistManagementItem() {
        new CheckListManagementPage(driver).openChecklistManagementPage();
    }

    @And("user verify that checklist management page is opened")
    public void userVerifyThatChecklistManagementPageIsOpened() {
        new BasePage(driver).checkUrlPage(data.get("urlBasePage"));
    }

    @When("user enters checklist data")
    public void userEntersChecklistData() throws InterruptedException {
        CheckListManagementPage cl = new CheckListManagementPage(driver);
        cl.enterChecklistData(data, data, data, data.get("rowLanguage"), data.get("itemType"), data.get("rowStatus"), data.get("testType"));
    }

    @When("user delete all checklists")
    public void userDeleteAllChecklists() throws InterruptedException {
        CheckListManagementPage cl = new CheckListManagementPage(driver);
        cl.deleteAllCheckLists(data.get("rowStatus"));
    }

    @Then("user should verify that all checklists are deleted")
    public void userShouldVerifyThatAllChecklistsAreDeleted() {
        CheckListManagementPage cl = new CheckListManagementPage(driver);
        cl.verifyCheckListEmpty();
    }

    @When("user edits check list data")
    public void userEditsCheckListData() {
        CheckListManagementPage cl = new CheckListManagementPage(driver);
        cl.editCheckList();
    }

    @And("user press save button")
    public void userPressSaveButton() {
        CheckListManagementPage cl = new CheckListManagementPage(driver);
        cl.clickSaveButton();
    }

    @Then("user should verify that checklist can not without header")
    public void userShouldVerifyThatChecklistCanNotWithoutHeader() {
        new CheckListManagementPage(driver).verifyEmptyCheckList(data.get("emptyCheckListMessage"));
    }

    @Then("user should verify that checklist can not be without item")
    public void userShouldVerifyThatChecklistCanNotBeWithoutItem() {
        new CheckListManagementPage(driver).verifyEmptyCheckList(data.get("emptyCheckListMessage"));
    }

    @Given("I am logged in {string} AND {string}")
    public void iAmLoggedInAND(String username, String password) throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.clickLoginButton();
        lp.signIn(username, password);
    }

    @Then("user should be verified login action {string} AND {string} AND {string}")
    public void userShouldBeVerifiedLoginActionANDAND(String testType, String expectedText, String attributeType) throws InterruptedException {
        new LoginPage(driver).signinVerification(testType, expectedText, attributeType);
    }

    @When("user creates new version checklist")
    public void userCreatesNewVersionChecklist() {
        CheckListManagementPage cp = new CheckListManagementPage(driver);
        cp.createNewVersionOfCheckList(data.get("rowStatus"));
    }

    @Then("user should verify version number")
    public void userShouldVerifyVersionNumber() {
        new CheckListManagementPage(driver).verifyVersionNumber(data.get("expectedText"));
    }
}