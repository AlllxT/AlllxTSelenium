package com.alllxt.selenium.framework.bases;

import com.alllxt.selenium.framework.utils.WebdriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by atribushny on 23.04.2017.
 */
public class BaseTest {
    protected static final String ADMIN_BASEURL = "http://localhost/litecart/admin/";
    protected static final int TIMEOUT = 10;

    protected static final String USER_NAME_TEXT_FIELD_CSS_LOCATOR = "[name='username']"; // or By.name
    protected static final String USER_PWD_TEXT_FIELD_CSS_LOCATOR = "[name='password']"; // or By.name
    protected static final String USER_LOGIN_BUTTON_CSS_LOCATOR = "button[name='login']";
    protected static final String LOGOUT_BUTTON_CSS_LOCATOR = "a[title='Logout']";
    protected static final String REMEMBER_ME_CHECKBOX = "input[name='remember_me']"; // or By.name

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        driver = WebdriverUtils.createNewDriver();
        wait = new WebDriverWait(driver, 60);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @BeforeMethod(alwaysRun = true)
    public void checkWebDriver() {
        WebdriverUtils.instantiateDriver();
    }

    @AfterClass(alwaysRun = true)
    public void dropDriver() {
        WebdriverUtils.quitDriver();
    }


}
