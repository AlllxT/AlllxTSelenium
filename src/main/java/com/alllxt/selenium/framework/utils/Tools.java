package com.alllxt.selenium.framework.utils;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by atribushny on 10.05.2017.
 */
public class Tools {

    static WebDriver driver = LocalDriverManager.getDriver();

    public static boolean titleIsPresent(final WebDriverWait wait, final String title) {
        return wait.until(titleIs(title));
    }


}
