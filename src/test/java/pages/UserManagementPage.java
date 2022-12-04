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

    @FindBy(css = "#close-technician-modal")
    WebElement closeTechnicianForm;
    @FindBy(css = "#user-firstname")
    WebElement technicianFirstName;
    @FindBy(css = "#user-lastname")
    WebElement technicianLastName;
    @FindBy(css = "#user-email")
    WebElement technicianEmail;

    @FindBy(xpath = "//*[text()=' Create ']")
    WebElement createTechnician;
    @FindBy(css = "#delete-technician-0")
    WebElement deleteTechnician;

    @FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody//tr")
    WebElement listOfTechnician;

    @FindBy(xpath = "//*[text()=' Add a technician ']")
    WebElement addTechnician;

    @FindBy(xpath = "//div[contains(@class,'visible-lg')]//input[@name='username']")
    WebElement user;

public void openUserManagementPage(){
    headerComponent.clickMenu();
    headerComponent.navigateToUserPage();
}
public void closeTechnician(){
    clickElement(closeTechnicianForm, "close button is pressed");
}
public void clickCreate(){
    clickElement(createTechnician, "create button is pressed ");
}
public void delet1stTechnician(){
    clickElement(deleteTechnician, "brisi prvog");
}
public void addTechnician(){
        clickElement(addTechnician, "add technician ");
}
public void deleteFirstTechnician(){
    GeneralPage gp = new GeneralPage(driver);
    List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
    for (WebElement product: listOfTechnicians) {
        if (product.getText().equals("No data available")) {
            System.out.println("list of technicians are empty !!!");
        } else{
            clickElement(deleteTechnician, "delete technician button is pressed");
            gp.clickConfirmOK();
            gp.clickConfirmOK();
        }
    }
}
public void deleteAllTechnicians(){
    GeneralPage gp = new GeneralPage(driver);
    List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
    if (listOfTechnicians.get(0).getText().equals("No data available")) {
        System.out.println("list of technicians are empty !!!");
    } else {
        for (int i = 0; i < listOfTechnicians.size(); i++) {
            clickElement(deleteTechnician, "delete technician button is pressed");
            gp.clickConfirmOK();
            gp.clickConfirmOK();
        }
    }
}

    public String randomTechnicianFirstName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "tech_FirstName_" + number;
        System.out.println("Random technician first name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }

    public String randomTechnicianLastName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(99, 199);
        String randomTechlastName = "tech_LastNae_" + number;
        System.out.println("Random technician last name exactly after generate is :" + randomTechlastName);
        return randomTechlastName;
    }

    public String randomTechnicianEmail() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 49);
        String randomTechEmail = "technician_+" + number + "@conti.com";
        System.out.println("Random technician email exactly after generate is :" + randomTechEmail);
        return randomTechEmail;
    }

    public void enterTehnicianData(Map<String, String> data,String randomTypeYesNo, String firstname, String lastname, String email) throws InterruptedException {
        int numTechnicans = Integer.parseInt(data.get("numTechnicians"));
        if(randomTypeYesNo.equalsIgnoreCase("yes")) {
            for (int i = 0; i < numTechnicans; i++) {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                typeText(technicianFirstName, randomTechnicianFirstName(), "First name random je upisan nakon generisanja i jel isti kao generisanja 1?");
                typeText(technicianLastName, randomTechnicianLastName(), "Last name");
                typeText(technicianEmail, randomTechnicianEmail(), "Email");
                clickCreate();
                new GeneralPage(driver).clickConfirmOK();
            }
        }else{
            typeText(technicianFirstName, firstname,"First name");
            typeText(technicianLastName, lastname,"Last name");
            typeText(technicianEmail, email,"Email");
        }
    }
}
