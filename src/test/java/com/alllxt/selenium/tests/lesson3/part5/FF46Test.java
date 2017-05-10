package com.alllxt.selenium.tests.lesson3.part5;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by atribushny on 18.04.2017.
 */
public class FF46Test extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private User user = new User();

    private String firefox46Path = "C:\\Program Files\\Mozilla FirefoxESR\\firefox.exe";

    @BeforeClass
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(FirefoxDriver.MARIONETTE, false);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefox46Path);
        options.addCapabilities(caps);
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, TIMEOUT);
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
        driver.get(ADMIN_BASEURL);
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
    public void tearDrown() {
        driver.quit();
    }
}
