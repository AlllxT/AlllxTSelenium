package com.alllxt.selenium.framework.utils;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import com.alllxt.selenium.framework.webdriver.manager.WebdriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by atribushny on 09.05.2017.
 */
public class WebdriverUtils {

    public static WebDriver createNewDriver() {
        WebDriver webDriver = WebdriverFactory.createInstance();
        LocalDriverManager.setWebDriver(webDriver);
        return webDriver;
    }

    public static void instantiateDriver(){
        WebDriver driver = LocalDriverManager.getDriver();
        try {
            if (driver == null){
                System.out.println("Webdriver is not set. Creating new driver");
                WebdriverUtils.createNewDriver();
                setMaximiseBrowserWindow();
                return;
            }
            driver.getWindowHandles();
            driver.getTitle();
        } catch (Exception exception){
            System.out.println("Browser is not detected. Error message: \""
                    + exception.getMessage() + "\". Restarting browser");
            WebdriverUtils.createNewDriver();
            setMaximiseBrowserWindow();
        }
    }

    public static void quitDriver() {
        if (LocalDriverManager.getDriver() != null) {
            try {
                LocalDriverManager.getDriver().quit();
                LocalDriverManager.setWebDriver(null);
            } catch (Exception exception) {
                String errorMSG = exception.getMessage();
                if (errorMSG == null) {
                    errorMSG = "The error message is empty.";
                }
            } finally {
                LocalDriverManager.setWebDriver(null);
            }
        }
    }

    public static void setMaximiseBrowserWindow(){
        WebDriver driver = LocalDriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }





}
