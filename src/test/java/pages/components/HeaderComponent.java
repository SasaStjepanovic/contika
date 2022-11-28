package pages.components;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    @FindBy(css = "#react-burger-menu-btn")
//    WebElement menu;


}