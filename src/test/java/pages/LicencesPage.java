package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LicencesPage extends BasePage{
    public LicencesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }




}
