package com.alllxt.selenium.tests.lesson2.part3;


import com.alllxt.selenium.framework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by atribushny on 19.04.2017.
 */
public class LoginToLiteCartTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private User user = new User();
    private static final String BASEURL = "http://localhost/litecart/admin/";
    private static final int TIMEOUT = 10;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    private static final String USER_NAME_TEXT_FIELD_CSS_LOCATOR = "[name='username']"; // or By.name
    private static final String USER_PWD_TEXT_FIELD_CSS_LOCATOR = "[name='password']"; // or By.name
    private static final String USER_LOGIN_BUTTON_CSS_LOCATOR = "button[name='login']";
    private static final String LOGOUT_BUTTON_CSS_LOCATOR = "a[title='Logout']";
    private static final String REMEMBER_ME_CHECKBOX = "input[name='remember_me']"; // or By.name


    /**
     * Go to Admin Login Page
     * If user is logged in - do logout
     * If Login button is visible - enter admin username and password.
     * Click Login
     */
    @Test
    public void loginToLineCartWithoutCheckboxTest() {
        driver.get(BASEURL);
        try {
            if (isElementVisible(LOGOUT_BUTTON_CSS_LOCATOR)) {
                findBy(LOGOUT_BUTTON_CSS_LOCATOR).click();
                waitForElementPresent(USER_LOGIN_BUTTON_CSS_LOCATOR);
            } else if (isElementVisible(USER_LOGIN_BUTTON_CSS_LOCATOR)) {
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).clear();
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).clear();
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).sendKeys(user.getAdminUserName());
                findBy(USER_PWD_TEXT_FIELD_CSS_LOCATOR).sendKeys(user.getAdminUserPassword());
                findBy(USER_LOGIN_BUTTON_CSS_LOCATOR).click();
                waitForElementPresent(LOGOUT_BUTTON_CSS_LOCATOR);
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    /**
     * Go to Admin Login Page
     * If user is logged in - do logout
     * If Login button is visible - enter admin username and password.
     * Click Remember checkbox.
     * Click Login
     */
    @Test
    public void loginToLineCartWithCheckboxTest() {
        driver.get(BASEURL);
        try {
            if (isElementVisible(LOGOUT_BUTTON_CSS_LOCATOR)) {
                findBy(LOGOUT_BUTTON_CSS_LOCATOR).click();
                waitForElementPresent(USER_LOGIN_BUTTON_CSS_LOCATOR);
            } else if (isElementVisible(USER_LOGIN_BUTTON_CSS_LOCATOR)) {
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).clear();
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).clear();
                findBy(USER_NAME_TEXT_FIELD_CSS_LOCATOR).sendKeys(user.getAdminUserName());
                findBy(USER_PWD_TEXT_FIELD_CSS_LOCATOR).sendKeys(user.getAdminUserPassword());
                findBy(REMEMBER_ME_CHECKBOX).click();
                findBy(USER_LOGIN_BUTTON_CSS_LOCATOR).click();
                waitForElementPresent(LOGOUT_BUTTON_CSS_LOCATOR);
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    private WebElement findBy(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    private void waitForElementPresent(String locator) {
        wait.until(ExpectedConditions.visibilityOf(findBy(locator)));
    }

    private boolean isElementVisible(String locator) {
        return driver.findElements(By.cssSelector(locator)).size() > 0;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
