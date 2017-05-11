package com.alllxt.selenium.litecart.pages.adminPages;

import com.alllxt.selenium.framework.bases.BasePage;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by atribushny on 24.04.2017.
 */
public class LitecartBasicPage extends BasePage {

    protected static final By LOGOUT_BUTTON_CSS_LOCATOR = By.cssSelector("a[title='Logout']");

    public void logout() {
        wait.until(visibilityOf(driver.findElement(LOGOUT_BUTTON_CSS_LOCATOR)));
        driver.findElement(LOGOUT_BUTTON_CSS_LOCATOR).click();
    }


}
