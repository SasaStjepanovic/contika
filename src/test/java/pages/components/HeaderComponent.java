package pages.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.v-btn__content")
    WebElement menu;

    @FindBy(css = "#header-avatar")
    WebElement profil;

    @FindBy(css = "div.logout")
    WebElement signout;

    @FindBy(xpath = "//*[text()=' Account settings ']")
    WebElement accountSettings;

    @FindBy(css = "#input-firstname")
    WebElement accountSettingsUserName;

    @FindBy(css = "#input-lastname")
    WebElement accountSettingsLastName;

    @FindBy(css = "button.warning")
    WebElement accountSettingsSave;

    @FindBy(xpath = "//*[text()='Ok']")
    WebElement accountSettingsOk;

    @FindBy(xpath = "//*[text()='Close']")
    WebElement accountSettingsClose;

    public void clickMenu(){
        clickElement(menu,"Menu button");
    }

    public void clickProfil(){
        clickElement(profil,"Profile button");
    }

    public void clickLogout(){
        clickElement(signout, "Logout button");
    }

    public void checkMenuItems(String[] menuItems) throws InterruptedException {
        List<WebElement> menu = driver.findElements(By.xpath("//*[@class='v-list-item__content']/div"));
        for (int i = 0; i< menu.size(); i++){
            String item = menu.get(i).getText();
            System.out.println("Actual element is: " + item);
            Thread.sleep(1000);
            Assert.assertEquals(item, menuItems[i], "Menu item not displayed");
            System.out.println("Menu item: " +item+ " is displayed");
            Assert.assertTrue(menu.get(i).isDisplayed(), menuItems[i]);
        }
    }


}