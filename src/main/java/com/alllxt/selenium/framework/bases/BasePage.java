package com.alllxt.selenium.framework.bases;

import com.alllxt.selenium.framework.utils.Tools;
import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.alllxt.selenium.framework.utils.Tools.getByFromString;
import static com.alllxt.selenium.framework.utils.Tools.waitForJSandJQueryToLoad;

/**
 * Created by atribushny on 24.04.2017.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public final String BASEURL = "http://localhost/litecart/";
    public final String ADMIN_BASEURL = BASEURL + "admin/";

    public BasePage() {
        this.driver = LocalDriverManager.getDriver();
        wait = new WebDriverWait(LocalDriverManager.getDriver(), 30);
        waitForJSandJQueryToLoad();
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        new Tools(wait);
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent(String locator) {
        return isElementPresent(getByFromString(locator));
    }


    public static void paintElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) LocalDriverManager.getDriver();
        String colorNew = String.format("background-color: %s", color);
        js.executeScript(String.format("arguments[0].setAttribute('style', '%s')", colorNew), element);
    }


}
