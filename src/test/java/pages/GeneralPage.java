package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage extends BasePage{
    public GeneralPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".swal2-confirm.swal2-styled")
    WebElement confirmOK;

    @FindBy(css = ".swal2-cancel.swal2-styled")
    WebElement confirmCancel;

    public void clickConfirmOK(){
        clickElement(confirmOK, "Confirm OK button ");
    }
    public void clickConfirmCancel(){
        clickElement(confirmCancel, "Confirm Cancel button ");
    }

}
