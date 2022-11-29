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
        new LoginPage(driver).signIn(data.get("userName"),data.get("password"),data.get("testType"),data.get("expectedText"));
    }

    @And("user clicks signin button")
    public void userClicksSigninButton() {
        new LoginPage(driver).clickLoginButton();
    }

    @And("hover")
    public void hover() throws InterruptedException {
        new LoginPage(driver).hoverOverUserName();
    }

    @And("signin again")
    public void signinAgain() {
        new LoginPage(driver).signinButton();
    }

    @Then("user should be verified successfully login")
    public void userShouldBeVerifiedSuccessfullyLogin() {
        new BasePage(driver).checkUrlPage(data.get("urlLicencesPage"));
    }

    @Then("user should be verified unsuccessfully login")
    public void userShouldBeVerifiedUnsuccessfullyLogin() {
        new LoginPage(driver).getAttribute(data.get("textValidationPage"));
    }
}