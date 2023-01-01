package pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    int waitTime = 30;

    public void explicitWait(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void scroll(String x, String y) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void compareText(WebElement element, String expectedText) {
        String actualTitle = element.getText();
        Assert.assertEquals(actualTitle, expectedText);
        System.out.println("Actual title is: " + actualTitle);
    }

    public void comparePartOfText(WebElement element, String expectedText) throws InterruptedException {
        String actualTitle = element.getText();
        Thread.sleep(5000);
        System.out.println("Actual title is: " + actualTitle);
        Thread.sleep(500);
        Assert.assertTrue(actualTitle.contains(expectedText), actualTitle);
    }

    public void getAttribute(WebElement element, String expectedValue, String attributeType) {
        String actualValue = element.getAttribute(attributeType);
        System.out.println("Actual value of element is : " + actualValue);
        Assert.assertEquals(actualValue, expectedValue);
    }

    public void clickElement(WebElement element, String log) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
            System.out.println("Clicked element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
            System.out.println("Clicked element: " + log);
        }
    }

    public void clickElementNoScrool(WebElement element, String log) {
        explicitWait(element);

        try {
            new Actions(driver).moveToElement(element).perform();
            element.click();
            System.out.println("Clicked element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
            System.out.println("Clicked element: " + log);
        }
    }

    public void clickElement(WebElement element) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
        }
    }

    public void checkUrlPage(String url) {
        String expextedUrl = "https://webportal-api-v2-2.val.eu-central-1.sindri.continental.cloud/" + url;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current url address: " + actualUrl);
        Assert.assertTrue(actualUrl.contains(expextedUrl), actualUrl);
    }

    public void typeText(WebElement element, String text, String log) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " to element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " to element: " + log);
        }
    }

    public void typeTextNoScrool(WebElement element, String text, String log) {
        explicitWait(element);

        try {
            new Actions(driver).moveToElement(element).perform();
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " to element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " to element: " + log);
        }
    }

    public void takeScreenshot(String name, String yesNo) throws IOException {
        if(yesNo.equalsIgnoreCase("YES")) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/results/screenshots/"+name+".png"));
        }
    }

    public void reportScreenshotAllure(String name, String desc, String yesOrNo) throws IOException {
        if(yesOrNo.equalsIgnoreCase("YES")) {
            long finish = System.currentTimeMillis();
            takeScreenshot(name+ "_" +finish, yesOrNo);
            Path path = Paths.get("src/results/screenshots/"+name+"_"+finish+".png");
            InputStream is = Files.newInputStream(path);
            Allure.addAttachment(desc,is);
        }
    }

}


