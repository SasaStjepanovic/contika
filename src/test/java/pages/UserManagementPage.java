package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderComponent;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserManagementPage extends BasePage {

    public HeaderComponent headerComponent;

    public UserManagementPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[contains(text(),'E-mail must be valid')]")
    WebElement invalidEmail;

    @FindBy(css = "#technician-search")
    WebElement searchFiled;
    @FindBy(xpath = "//*[contains(text(),' Username provided already exists ')]")
    WebElement userAlreadyExists;

    @FindBy(xpath = "//div[@class='v-alert__content']")
    WebElement successfullyCreated;
    @FindBy(css = "#close-technician-modal")
    WebElement closeTechnicianForm;

    @FindBy(css = "#close-edit-technician-modal")
    WebElement closeEditTechnicianForm;
    @FindBy(css = "#user-firstname")
    WebElement technicianFirstName;
    @FindBy(css = "#user-lastname")
    WebElement technicianLastName;
    @FindBy(css = "#user-email")
    WebElement technicianEmail;

    @FindBy(css = "#technician-edit-firstname")
    WebElement editTechnicianFristName;

    @FindBy(css = "#technician-edit-lastname")
    WebElement editTechnicianLastName;

    @FindBy(css = "#technician-edit-username")
    WebElement editTechnicianEmail;

    @FindBy(css = "#update-technician")
    WebElement updateInformation;

    @FindBy(xpath = "//*[text()=' Create ']/..")
    WebElement createTechnician;
    @FindBy(css = "#delete-technician-0")
    WebElement deleteTechnician;

    @FindBy(css = "#edit-technician-0")
    WebElement editTechnician;

    @FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody//tr")
    WebElement listOfTechnician;

    @FindBy(xpath = "//*[text()=' Add a technician ']")
    WebElement addTechnician;

    @FindBy(css = "#remove-technician")
    WebElement removeAllocatedButton;
    @FindBy(xpath = "//*[text()='Licence allocated']")
    WebElement licenceAllocatedButton;

    @FindBy(xpath = "//*[text()=' Remove allocation ']")
    WebElement removeAllocationButton;

    @FindBy(css = "#technician-0-name")
    WebElement searchedTechnician;

    @FindBy(xpath = "//*[text()='Device associated']")
    WebElement deviceAllocatedButton;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='username']")
    WebElement user;

    public void openUserManagementPage() {
        headerComponent.clickMenu();
        headerComponent.navigateToUserPage();
    }

    GeneralPage gp = new GeneralPage(driver);

    public void serachTehcnican(String searchTehnician){
        typeText(searchFiled, searchTehnician, "name of technician is entered in search filed");
    }
    public void clickEditButton(){
        clickElement(editTechnician, "edit technician button");
    }

    public void VerifySearchedText(String searchedText) throws InterruptedException {
        comparePartOfText(searchedTechnician, searchedText);
    }

    public void clearSearch(String emptySearch){
        typeText(searchFiled, emptySearch, "Clear search field");
    }
    public void verifyUserAlreadyExists(String expextedText){
        compareText(userAlreadyExists, expextedText);
    }
    public void verifyInvalidEmail(String expextedText){
        compareText(invalidEmail,expextedText);
    }

    public void closeTechnician() {
        clickElement(closeTechnicianForm, "close button is pressed");
    }

    public void closeEditTechnician(){
        clickElement(closeEditTechnicianForm,"edit technician winodw is closed");
    }
    public void clickCreate() {
        clickElement(createTechnician, "create button is pressed ");
    }

    public void delet1stTechnician() {
        clickElement(deleteTechnician, "brisi prvog");
    }

    public void addTechnician() {
        clickElement(addTechnician, "add technician ");
    }

    public void deleteFirstTechnician() {
        GeneralPage gp = new GeneralPage(driver);
        List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
        for (WebElement product : listOfTechnicians) {
            if (product.getText().equals("No data available")) {
                System.out.println("list of technicians are empty !!!");
            } else {
                clickElement(deleteTechnician, "delete technician button is pressed");
                gp.clickConfirmOK();
                gp.clickConfirmOK();
            }
        }
    }


    public void deleteAllTechnicians(String rowNumber) throws InterruptedException {
        gp.rowsPerPage(rowNumber);
        List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
        if (listOfTechnicians.get(0).getText().equals("No data available")) {
            System.out.println("list of technicians are empty !!!");
        } else {
            for (int i = 0; i < listOfTechnicians.size(); i++) {
                clickElementNoScrool(deleteTechnician, "delete technician button is pressed");
                gp.clickConfirmOK();
                Thread.sleep(200);
                gp.clickConfirmOK();
            }
        }
    }

    public String randomTechnicianFirstName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "tech_FirstName_Sasa_" + number;
        System.out.println("Random technician first name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }

    public String randomTechnicianLastName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(99, 199);
        String randomTechlastName = "tech_LastName_Sasa_" + number;
        System.out.println("Random technician last name exactly after generate is :" + randomTechlastName);
        return randomTechlastName;
    }

    public String randomTechnicianEmail() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 49);
        String randomTechEmail = "technician_sasa+" + number + "@conti.com";
        System.out.println("Random technician email exactly after generate is :" + randomTechEmail);
        return randomTechEmail;
    }


    public void enterTehnicianData(Map<String, String> data, String randomTypeYesNo, String firstname, String lastname, String email) throws InterruptedException {
        int numTechnicans = Integer.parseInt(data.get("numTechnicians"));
        if (randomTypeYesNo.equalsIgnoreCase("yes")) {
            for (int i = 0; i < numTechnicans; i++) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                typeText(technicianFirstName, randomTechnicianFirstName(), "First name random je upisan nakon generisanja i jel isti kao generisanja 1?");
                typeText(technicianLastName, randomTechnicianLastName(), "Last name");
                typeText(technicianEmail, randomTechnicianEmail(), "Email");
                clickCreate();
                new GeneralPage(driver).clickConfirmOK();
            }
        } else {
            typeText(technicianFirstName, firstname, "First name");
            typeText(technicianLastName, lastname, "Last name");
            typeText(technicianEmail, email, "Email");
            if (createTechnician.isEnabled()){
                clickCreate();
                new GeneralPage(driver).clickConfirmOK();
            } else {
                System.out.println("Click button is not enabled");
            }

        }
    }

    public void editTechnician(String randomTypeYesNo,String firstname, String lastname, String email, String randomFirst, String randomLast, String randomEmail){
        clickEditButton();
        if (randomTypeYesNo.equalsIgnoreCase("yes")) {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            typeText(editTechnicianFristName, randomFirst, "Random First name after editing");
            typeText(editTechnicianLastName, randomLast, "Random Last name after editing");
            typeText(editTechnicianEmail, randomEmail, "Random Email");
            clickElement(updateInformation, "update information button after editing");
            new GeneralPage(driver).clickConfirmOK();
            closeEditTechnician();
            }
         else {
            typeText(editTechnicianFristName, firstname, "First name");
            typeText(editTechnicianLastName, lastname, "Last name");
            typeText(editTechnicianEmail, email, "Email");
            clickElement(updateInformation, "update information button");
            new GeneralPage(driver).clickConfirmOK();
            closeEditTechnician();
        }
    }

    public void verifyEditedTechnicians( String attributeType,String randomFirsTechnician, String randomLastTechnician, String randomEmailTechnician) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        getAttribute(editTechnicianFristName, randomFirsTechnician, attributeType);
        getAttribute(editTechnicianLastName, randomLastTechnician, attributeType);
        getAttribute(editTechnicianEmail, randomEmailTechnician, attributeType);
        closeEditTechnician();
    }

    public void licenceAllocated(){
        clickElement(editTechnician, "edit technician button");
        try{
            clickElement(licenceAllocatedButton, "licence allocated button");
        } catch (Exception e) {
            e.printStackTrace();
            clickElement(licenceAllocatedButton, "licence allocated button");
        }
    }

    public void verifyRemoveAllocationButton(String expextedText){
        if (!removeAllocatedButton.isEnabled()){
            compareText(removeAllocationButton, expextedText);
        } else {
            System.out.println("Remove button is enabled");
            clickElement(removeAllocatedButton, "Remove allocation button is pressed");
        }
    }

    public void verifyTechnicianListEmpty(){
        List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
        if (listOfTechnicians.get(0).getText().equals("No data available")) {
            System.out.println("list of technicians are empty !!!");
        } else {
            System.out.println("There are still exist technicians at the list");
        }
    }
}
