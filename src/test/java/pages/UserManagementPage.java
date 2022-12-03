package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderComponent;

public class UserManagementPage extends BasePage {

    public HeaderComponent headerComponent;

    public UserManagementPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".login-btn.v-btn>span")
    WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='username']")
    WebElement user;

public void openUserManagementPage(){
    headerComponent.clickMenu();
    headerComponent.navigateToUserPage();
}

}
