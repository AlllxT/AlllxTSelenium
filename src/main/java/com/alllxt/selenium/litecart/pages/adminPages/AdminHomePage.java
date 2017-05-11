package com.alllxt.selenium.litecart.pages.adminPages;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.alllxt.selenium.framework.utils.Tools.click;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class AdminHomePage extends LitecartBasicPage {


    private WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), 10);

    private static final String MENU_BLOCK = "box-apps-menu";
    private static final String MENU_LIST = "li#app-";
    private static final String MENU_OPTION_SELECTED = "#app-.selected";
    private static final String MENU_SUB_OPTION = "ul>li";
    private static final String HEADER = "#main h1";

    private List<WebElement> getMenuOptions() {
        return driver.findElements(By.cssSelector(MENU_LIST));
    }

    private List<WebElement> getSubMenuOptions(WebElement element) {
        return element.findElements(By.cssSelector(MENU_SUB_OPTION));
    }

    public boolean isHomePageLoaded() {
        return wait.until(ExpectedConditions.urlToBe(ADMIN_BASEURL));
    }

    private WebElement getSelectedMenuOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(MENU_OPTION_SELECTED)));
        return driver.findElement(By.cssSelector(MENU_OPTION_SELECTED));
    }

    public void checkMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MENU_BLOCK)));
        for (int index = 0; index < getMenuOptions().size(); index++) {
            WebElement menuOption = getMenuOptions().get(index);
            System.out.println("# " + menuOption.getText());
            click(menuOption);
            wait.until(ExpectedConditions.stalenessOf(menuOption));
            for (int subIndex = 0; subIndex < getSubMenuOptions(getSelectedMenuOption()).size(); subIndex++) {
                WebElement subMenuOption = getSubMenuOptions(getSelectedMenuOption()).get(subIndex);
                System.out.println("++ " + subMenuOption.getText());
                click(subMenuOption);
                wait.until(ExpectedConditions.stalenessOf(subMenuOption));
                assertTrue(isElementPresent(HEADER));
            }
        }
    }


}


