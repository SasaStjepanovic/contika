package pages.components;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='v-list-item__content']//div[text()='User management']")
    WebElement userPage;
    @FindBy(xpath = "//*[@class='v-list-item__content']//div[text()='Licence Overview']")
    WebElement licencePage;
    @FindBy(xpath = "//*[@class='v-list-item__content']//div[text()='Checklist management']")
    WebElement checklistPage;
    @FindBy(css = "span.v-btn__content")
    WebElement menu;
    @FindBy(css = "#header-avatar")
    WebElement profil;
    @FindBy(css = "div.logout")
    WebElement signout;
    @FindBy(xpath = "//*[text()=' Account settings ']")
    WebElement accountSettings;
    @FindBy(css = "#input-firstname")
    WebElement accountFirstName;
    @FindBy(css = "#input-lastname")
    WebElement accountLastName;
    @FindBy(css = "button.warning")
    WebElement accountSettingsSave;
    @FindBy(xpath = "//button[contains(@disabled,'disabled')]")
    WebElement accountSettingsSaveDisbaled;
    @FindBy(xpath = "//*[contains(text(),'Your information was successfully saved')]")
    WebElement successfullySaved;

    @FindBy(xpath = "//*[contains(text(),'Checklist successfully saved.')]")
    WebElement successfullySavedCheckList;

    @FindBy(xpath = "//*[text()='Ok']")
    WebElement accountSettingsOk;
    @FindBy(xpath = "//*[text()='Close']")
    WebElement accountSettingsClose;
    @FindBy(xpath = "//input[@id='input-firstname']/../../..//div[2]//*[@class='v-messages__message']")
    WebElement firstnameAccountEmptyMessage;
    @FindBy(xpath = "//input[@id='input-lastname']/../../..//div[2]//*[@class='v-messages__message']")
    WebElement lastnameAccountEmptyMessage;

    public void navigateToMenu(String pageName) throws Exception {
        switch (pageName.toLowerCase()) {
            case "licence": {
                navigateToLicencePage();
            }
            break;
            case "user": {
                navigateToUserPage();
            }
            break;
            case "checklist": {
                navigateToCheckListPage();
            }
            break;
            default:
                throw new Exception("No such page: " + pageName);
        }
    }
    public void navigateToLicencePage(){
        clickElement(licencePage,"licence page");
    }
    public void navigateToUserPage(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickElement(userPage,"user page");
        String expextedUrl = "https://webportal-api-v2-2.val.eu-central-1.sindri.continental.cloud/users";
        String actualUrl = driver.getCurrentUrl();
        if (expextedUrl.equals(actualUrl)){
            System.out.println("Do nothing, User Management page already opened");
        } else {
            clickElement(userPage,"user page");
        }
    }
    public void navigateToCheckListPage(){
        clickElement(checklistPage,"checklist page");
    }

    public void isSaveButtonDisabled(String expectedValue, String attributeType){
        getAttribute(accountSettingsSaveDisbaled,expectedValue, attributeType);
    }
    public void clickAccount(){
        clickElement(accountSettings, "Account settings");
    }
    public void clickMenu(){
        clickElement(menu,"Menu button");
    }
    public void clickProfil(){
        clickElement(profil,"Profile button");
    }
    public void clickLogout(){
        clickElement(signout, "Logout button");
    }
    public void clickSaveButton(){
        clickElement(accountSettingsSave, "Save button Account settings ");
    }
    public void clickCloseButton(){
        clickElement(accountSettingsClose, "Close button Account settings ");
    }
    public void savedVerification(String expectedText){
        compareText(successfullySaved, expectedText);
    }

    public void savedCheckListVerification(String expectedText){
        compareText(successfullySavedCheckList, expectedText);
    }

    public void accountSettingsMessageVerification(String expectedText1, String expectedText2){
        compareText(firstnameAccountEmptyMessage, expectedText1);
        compareText(lastnameAccountEmptyMessage, expectedText2);
    }

    public String randomFirstName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 9);
        String randomFirst = "sasa_" + number;
        System.out.println("Random first name exactly after generate_1 is :" + randomFirst);

        return randomFirst;
    }

    public String randomLastName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(10, 20);
        String randomlast = "stjepanovic_" + number;
        System.out.println("Random last name exactly after generate is :" + randomlast);
        return randomlast;
    }

    public void enterFirstAndLastName(String randomTypeYesNo, String firstname, String lastname, String randomFirstname, String randomLastname) throws InterruptedException {
        if(randomTypeYesNo.equalsIgnoreCase("yes")) {
            typeText(accountFirstName, randomFirstname, "First name random je upisan nakon generisanja jel isti kao generisanja_1?");
            typeText(accountLastName, randomLastname, "Last name");
        }else{
            typeText(accountFirstName, firstname,"First name");
            typeText(accountLastName, lastname,"Last name");
        }
    }
    public void verifyCredentialsAfterRandomChanges( String attributeType,String randomFirstName, String randomlastName) {
            getAttribute(accountFirstName, randomFirstName, attributeType);
            getAttribute(accountLastName, randomlastName, attributeType);
    }

    public void verifyCredentialsAfterNotRandomChanges( String attributeType,String firstname,String lastname) {
            getAttribute(accountFirstName, firstname, attributeType);
            getAttribute(accountLastName, lastname, attributeType);
    }

    public void checkMenuItems(String[] menuItems) throws InterruptedException {
        List<WebElement> menu = driver.findElements(By.xpath("//*[@class='v-list-item__content']/div"));
        for (int i = 0; i< menu.size(); i++){
            Thread.sleep(1000);
            String item = menu.get(i).getText();
            System.out.println("Test** Actual element is: " + item);
            Thread.sleep(1000);
            Assert.assertEquals(item, menuItems[i], "Menu item not displayed");
            System.out.println("Menu item: " +item+ " is displayed");
            Assert.assertTrue(menu.get(i).isDisplayed(), menuItems[i]);
        }
    }


}