package com.alllxt.selenium.litecart.pages.adminPages;

import com.alllxt.selenium.framework.bases.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.alllxt.selenium.framework.utils.Tools.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by atribushny on 24.04.2017.
 */
public class LitecartBasicPage extends BasePage {


    protected static final By LOGOUT_BUTTON_CSS_LOCATOR = By.cssSelector("a[title='Logout']");
    protected static final String MENU_BLOCK = "box-apps-menu";
    protected static final String MENU_LIST = "li#app-";
    protected static final String MENU_OPTION_SELECTED = "#app-.selected";
    protected static final String MENU_SUB_OPTION = "ul>li";
    protected static final String HEADER = "#main h1";

    public void logout() {
        wait.until(visibilityOf(driver.findElement(LOGOUT_BUTTON_CSS_LOCATOR)));
        driver.findElement(LOGOUT_BUTTON_CSS_LOCATOR).click();
    }

    public void openCatalog() {
        clickOnElementInMenu("Catalog");
    }

    public void openCountries() {
        clickOnElementInMenu("Countries");
        waitForJSandJQueryToLoad();
        wait.until(visibilityOfElementLocated(getByFromString(MENU_OPTION_SELECTED)));
    }

    private static void clickOnElementInMenu(String subMenuName) {
        findElementInElement(findElement(MENU_LIST), String.format("//li[@id='app-']//span[.='%s']", subMenuName)).click();
    }

    protected WebElement getSelectedMenuOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_OPTION_SELECTED)));
        return driver.findElement(By.cssSelector(MENU_OPTION_SELECTED));
    }


    protected List<WebElement> getMenuOptions() {
        return driver.findElements(By.cssSelector(MENU_LIST));
    }

    protected List<WebElement> getSubMenuOptions(WebElement element) {
        return element.findElements(By.cssSelector(MENU_SUB_OPTION));
    }


}
