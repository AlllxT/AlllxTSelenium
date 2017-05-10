package com.alllxt.selenium.framework.bases;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by atribushny on 24.04.2017.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public final String BASEURL = "http://localhost/litecart/";
    public final String ADMIN_BASEURL = BASEURL + "admin";

    public BasePage() {
        this.driver = LocalDriverManager.getDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(String locator) {
        if (locator.startsWith("//")
                || locator.startsWith(".//")
                || locator.startsWith("./")) {
            return driver.findElements(By.xpath(locator)).size() > 0;
        } else {
            return driver.findElements(By.cssSelector(locator)).size() > 0;
        }
    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }


    protected WebElement findByCss(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }


}
