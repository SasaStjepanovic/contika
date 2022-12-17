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

public class CheckListManagementPage extends BasePage {

    public HeaderComponent headerComponent;

    public CheckListManagementPage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=' Create a checklist ']")
    WebElement createChecklist;

    @FindBy(xpath = "//div[@class='v-data-table__wrapper']//tbody/tr//td[6]/span//button[1]")
    WebElement editChecklist;

    @FindBy(css = "#checklist-name")
    WebElement checklistName;

    @FindBy(xpath = "//*[@id='save-checklist-btn']")
    WebElement saveButton;

    @FindBy(xpath = "//*[text()='Back to Checklist']")
    WebElement backButton;

    @FindBy(css = "#swal2-content")
    WebElement checkListEmpty;

    @FindBy(css = "#add-header-btn")
    WebElement addHeader;

    @FindBy(css = "#language-list")
    WebElement expandLanguage;

    @FindBy(css = "#status-list")
    WebElement expandStatus;

    public void openChecklistManagementPage() {
        headerComponent.clickMenu();
        headerComponent.navigateToCheckListPage();
    }

    public void verifyEmptyCheckList(String expectedText){
        compareText(checkListEmpty, expectedText);
    }

    public void clickEditButton() {
        clickElement(editChecklist, "edit button is pressed");
    }

    public void clickBackButton() {
        clickElementNoScrool(backButton, "back button is pressed");
    }

    public void clickSaveButton() {
        clickElement(saveButton, "save button is pressed");
    }

    public void pressCreateChecklistButton() {
//        clickElement(createChecklist, "create checklist button is pressed");
        clickElementNoScrool(createChecklist, "create checklist button is pressed");
    }

    public void pressAddHeaderButton() {
        clickElement(addHeader, "add header button is pressed");
    }

    public void chooseLanguage(String rowNumber) {
        int rowLanguage = Integer.parseInt(rowNumber);
        clickElement(expandLanguage, "Langugaes dropdown menu is expanded");
        WebElement rowAll = driver.findElement(By.xpath("//input[@id='language-list']/../../../../../div[2]/div//div[" + rowLanguage + "]"));
        System.out.println("Row number: " + rowLanguage);
        clickElement(rowAll, "language is selected");
    }

    public void chooseStatus(String rowNumber) {
        int rowStatus = Integer.parseInt(rowNumber);
        clickElementNoScrool(expandStatus, "status dropdown menu is expanded");
        WebElement rowAll = driver.findElement(By.xpath("//input[@id='status-list']/../../../../../div[2]/div//div[" + rowStatus + "]"));
        System.out.println("Row number: " + rowStatus);
//        clickElement(rowAll, "status is selected");
        clickElementNoScrool(rowAll, "status is selected");
    }

    public void clickItemType(WebElement element) {
        clickElement(element, "Item type is selected");
    }

    public String randomCheckListName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "checkList_Sasa_" + number;
        System.out.println("Random check list name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }

    public String randomHeaderName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "header_Sasa_" + number;
        System.out.println("Random header name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }

    public String randomItemName() {
        Faker fakerData = new Faker();
        int number = fakerData.number().numberBetween(1, 99);
        String randomTechFirstName = "item_Sasa_" + number;
        System.out.println("Random item name exactly after generate is :" + randomTechFirstName);
        return randomTechFirstName;
    }

    public void enterChecklistData(Map<String, String> data1, Map<String, String> data2, Map<String, String> data3, String rowLanguage, String itemType, String rowStatus, String testType) throws InterruptedException {

        int numHeaders = Integer.parseInt(data1.get("numHeaders"));
        int numItem = Integer.parseInt(data2.get("numItems"));
        int numChecklists = Integer.parseInt(data3.get("numChecklists"));
        if (testType.equalsIgnoreCase("positive")) {
            for (int n = 0; n < numChecklists; n++) {
                pressCreateChecklistButton();
                typeText(checklistName, randomCheckListName(), "checklistname is entered");
                chooseLanguage(rowLanguage);
                chooseStatus(rowStatus);
                for (int i = 1; i <= numHeaders; i++) {
                    pressAddHeaderButton();
                    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                    WebElement headerName = driver.findElement(By.xpath("//*[@class='headers']/div[" + (i) + "]//input[contains(@id,'header-name')]"));
                    typeText(headerName, randomHeaderName(), "randomHeader name is entered");

                }
                for (int j = 1; j <= numItem; j++) {
                    scrollToElement(expandLanguage);
                    while (j == 1) {
                        WebElement addIteam = driver.findElement(By.xpath("//*[@class='headers']/div[1]//*[contains(@class,'addItem')]"));
                        Thread.sleep(3000);
                        clickElementNoScrool(addIteam, "ADD ITEM_" + j + " button is pressed");
                        WebElement nameIteam1 = driver.findElement(By.xpath("//*[@class='headers']/div[" + j + "]/div[" + (j + 1) + "]//*[contains(@id,'item-name-')]"));
                        typeText(nameIteam1, randomItemName(), "randomItem name is entered");
                        WebElement itemTypeField = driver.findElement(By.xpath("//*[@class='headers']/div[1]/div[2]//*[contains(@id,'item-type-')]"));
                        clickElement(itemTypeField, "item type is expanded");
                        WebElement itemTypeAll = driver.findElement(By.xpath("//*[@class='headers']/div[1]//*[contains(@role,'listbox')]//div[" + itemType + "]"));
                        clickItemType(itemTypeAll);
                        break;
                    }
                    while (j == 2) {
                        WebElement addIteam = driver.findElement(By.xpath("//*[@class='headers']/div[" + j + "]//*[contains(@class,'addItem')]"));
                        Thread.sleep(3000);
                        clickElementNoScrool(addIteam, "ADD ITEM_" + j + " button is pressed");
                        WebElement nameIteam2 = driver.findElement(By.xpath("//*[@class='headers']/div[" + j + "]/div[" + j + "]//*[contains(@id,'item-name-')]"));
                        typeText(nameIteam2, randomItemName(), "randomItem name is entered");
                        WebElement itemTypeField = driver.findElement(By.xpath("//*[@class='headers']/div[2]/div[2]//*[contains(@id,'item-type-')]"));
                        clickElement(itemTypeField, "item type is expanded");
                        WebElement itemTypeAll = driver.findElement(By.xpath("//*[@class='headers']/div[2]//*[contains(@role,'listbox')]//div[" + itemType + "]"));
                        clickItemType(itemTypeAll);
                        break;
                    }
                }
                clickSaveButton();
                new GeneralPage(driver).clickConfirmOK();
                clickBackButton();
            }
        } else {
            pressCreateChecklistButton();
            typeText(checklistName, randomCheckListName(), "checklistname is entered");
            for (int i = 1; i <= numHeaders; i++) {
                pressAddHeaderButton();
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                WebElement headerName = driver.findElement(By.xpath("//*[@class='headers']/div[" + (i) + "]//input[contains(@id,'header-name')]"));
                typeText(headerName, randomHeaderName(), "randomHeader name is entered");
            }
            for (int j = 1; j <= numItem; j++) {
                WebElement addIteam = driver.findElement(By.xpath("//*[@class='headers']/div[1]//*[contains(@class,'addItem')]"));
                Thread.sleep(3000);
                clickElementNoScrool(addIteam, "ADD ITEM_" + j + " button is pressed");
                WebElement nameIteam1 = driver.findElement(By.xpath("//*[@class='headers']/div[" + j + "]/div[" + (j + 1) + "]//*[contains(@id,'item-name-')]"));
                typeText(nameIteam1, randomItemName(), "randomItem name is entered");
                WebElement itemTypeField = driver.findElement(By.xpath("//*[@class='headers']/div[1]/div[2]//*[contains(@id,'item-type-')]"));
                clickElement(itemTypeField, "item type is expanded");
                WebElement itemTypeAll = driver.findElement(By.xpath("//*[@class='headers']/div[1]//*[contains(@role,'listbox')]//div[" + itemType + "]"));
                clickItemType(itemTypeAll);
            }
        }
    }

    public void deleteAllCheckLists(String rowStatus) throws InterruptedException {
        List<WebElement> listOfCheckList = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
        if (listOfCheckList.get(0).getText().equals("No data available")) {
            System.out.println("list of technicians are empty !!!");
        } else {
            for (int i = 0; i < listOfCheckList.size(); i++) {
                GeneralPage gp = new GeneralPage(driver);
                clickEditButton();
                WebElement element = driver.findElement(By.xpath("//*[@id='status-list']/.."));
                String statusText = element.getText();
                if (statusText.equals("Draft")) {
                    System.out.println("Draft status already selected");
                    chooseStatus("2");
                    clickSaveButton();
                    gp.clickConfirmOK();
                    chooseStatus("2");
                    clickSaveButton();
                    gp.clickConfirmOK();
                } else {
                    chooseStatus("2");
                    clickSaveButton();
                    gp.clickConfirmOK();
                }
                clickBackButton();
            }
        }
    }

    public void verifyCheckListEmpty() {
        List<WebElement> listOfTechnicians = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']//tbody//tr"));
        if (listOfTechnicians.get(0).getText().equals("No data available")) {
            System.out.println("list of checklists are empty !!!");
        } else {
            System.out.println("There are still exist checklist at the list");
        }
    }

    public void editCheckList() {
        clickEditButton();
        typeText(checklistName, randomCheckListName(), "edit checklistname is entered");
        WebElement headerName = driver.findElement(By.xpath("//*[@class='headers']/div[1]//input[contains(@id,'header-name')]"));
        typeText(headerName, randomHeaderName(), "edit randomHeader name is entered");
        WebElement nameIteam1 = driver.findElement(By.xpath("//*[@class='headers']/div[1]/div[2]//*[contains(@id,'item-name-')]"));
        typeText(nameIteam1, randomItemName(), "edit randomItem name is entered");

        clickSaveButton();
        new GeneralPage(driver).clickConfirmOK();
        clickBackButton();
    }
}
