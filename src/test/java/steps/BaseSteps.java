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
import pages.LoginPage;
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
}