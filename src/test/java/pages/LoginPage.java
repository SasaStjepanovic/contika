package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".login-btn.v-btn")
    WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='username']")
    WebElement username;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='signInSubmitButton']")
    WebElement signin;

    @FindBy(xpath = "//h1[text()='All my licences']")
    WebElement titleAfterSuccessfullyLogin;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//p[@id='loginErrorMessage']")
    WebElement loginErrorMessage;

    public void clickLoginButton(){
        clickElement(loginButton, "Login button is pressed: ");
    }
    public void enterUsername(String value){
        typeText(username,value, "Username input field");
    }
    public void enterPassword(String value){
        typeText(password,value, "Password input field");
    }
    public void signin(){
        clickElement(signin, "Signin button is pressed");
    }

    public void signIn(String username, String password, String testType, String expectedText) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickElement(signin, "signin button is pressed");

        if(testType.equalsIgnoreCase("positive")){
            compareText(titleAfterSuccessfullyLogin,expectedText);
        } else {
            compareText(loginErrorMessage,expectedText);
        }

    }
}