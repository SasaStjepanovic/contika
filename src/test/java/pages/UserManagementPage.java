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

    @FindBy(xpath = "//*[text()=' Add a technician ']")
    WebElement addTechnician;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='username']")
    WebElement user;

public void openUserManagementPage(){
    headerComponent.clickMenu();
    headerComponent.navigateToUserPage();
}

public void addTechnician(){
    clickElement(addTechnician, "add technician ");
}

}
