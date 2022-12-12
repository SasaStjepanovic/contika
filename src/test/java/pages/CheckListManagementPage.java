package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.components.HeaderComponent;

public class CheckListManagementPage extends BasePage{

    public HeaderComponent headerComponent;

    public CheckListManagementPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=' Create a checklist ']")
    WebElement createChecklist;

    @FindBy(css = "#checklist-name")
    WebElement checklistName;

    @FindBy(xpath = "#language-list")
    WebElement listOgLangugaes;

    @FindBy(css = "#status-list")
    WebElement listOfStatus;

    @FindBy(css = "#add-header-btn")
    WebElement addHeader;

    @FindBy(css = "#language-list")
    WebElement expandLanguage;

    public void openChecklistManagementPage() {
        headerComponent.clickMenu();
        headerComponent.navigateToCheckListPage();
    }

    public void pressCreateChecklistButton(){
        clickElement(createChecklist, "create checklist button is pressed");
    }
    public void pressAddHeaderButton(){
        clickElement(addHeader, "add header button is pressed");
    }

    public void checkLanguage(String rowNumber){
        int rowLanguage = Integer.parseInt(rowNumber);
        clickElement(expandLanguage, "Langugaes dropdown menu is expanded");
        WebElement rowAll = driver.findElement(By.xpath("//*[@id='list-150']//div["+rowLanguage+"]"));
        System.out.println("Row number: " + rowLanguage);
        clickElement(rowAll, "language is selected");
    }
    public String randomCheckListName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "checkList_Sasa_" + number;
        System.out.println("Random check list name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }
    public void enterChecklistData(String rowLanguage, String randomCheckListName){
        pressCreateChecklistButton();
        typeText(checklistName, randomCheckListName, "checklistname is entered");
        checkLanguage(rowLanguage);
        pressAddHeaderButton();
    }

}
