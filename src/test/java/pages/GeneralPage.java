package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralPage extends BasePage {
    public GeneralPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mdi-menu-down.theme--light")
    WebElement rowsPerPageDownArrow;

    @FindBy(css = ".swal2-confirm.swal2-styled")
    WebElement confirmOK;

    @FindBy(xpath = "//*[@role='listbox']//div")
    WebElement listOfRowNumbers;

    @FindBy(css = ".swal2-cancel.swal2-styled")
    WebElement confirmCancel;

    public void clickDownArrow() {
        clickElement(rowsPerPageDownArrow, "down arrow is clicked");
    }

    public void clickConfirmOK() {
        clickElement(confirmOK, "Confirm OK button ");
    }

    public void clickConfirmCancel() {
        clickElement(confirmCancel, "Confirm Cancel button ");
    }

    public void rowsPerPage(String rowNumber) {
        int num = Integer.parseInt(rowNumber);
        clickDownArrow();
        WebElement rowAll = driver.findElement(By.xpath("//*[@role='listbox']//div[" + num + "]"));
        System.out.println("Row number: " + num);
        clickElement(rowAll, "row is selected");
        scroolTop();
    }

    public void scroolTop() {
        scroll("0", "0");
    }
}
