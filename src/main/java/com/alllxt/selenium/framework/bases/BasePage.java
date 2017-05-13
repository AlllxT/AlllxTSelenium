package com.alllxt.selenium.framework.bases;

import com.alllxt.selenium.framework.utils.Tools;
import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.alllxt.selenium.framework.utils.Tools.getByFromString;

/**
 * Created by atribushny on 24.04.2017.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), 30);


    public final String BASEURL = "http://localhost/litecart/";
    public final String ADMIN_BASEURL = BASEURL + "admin/";

    public BasePage() {
        this.driver = LocalDriverManager.getDriver();
        Tools tools = new Tools(wait);
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent(String locator) {
        return isElementPresent(getByFromString(locator));
    }


    protected WebElement findByCss(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public static void paintElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) LocalDriverManager.getDriver();
        String colorNew = String.format("background-color: %s", color);
        js.executeScript(String.format("arguments[0].setAttribute('style', '%s')", colorNew), element);
    }


}
