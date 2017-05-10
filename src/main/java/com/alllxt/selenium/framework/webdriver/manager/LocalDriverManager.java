package com.alllxt.selenium.framework.webdriver.manager;

import org.openqa.selenium.WebDriver;

/**
 * Created by atribushny on 09.05.2017.
 */
public class LocalDriverManager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver){
        webDriver.set(driver);
    }
}
