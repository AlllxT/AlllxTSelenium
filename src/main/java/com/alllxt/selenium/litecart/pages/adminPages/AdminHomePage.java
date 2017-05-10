package com.alllxt.selenium.litecart.pages.adminPages;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class AdminHomePage extends LitecartBasicPage {

    private WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), 10);

    private static final String MENU_BLOCK = "box-apps-menu";
    private static final String MENU_LIST = "app-";
    private static final String MENU_OPTION_SELECTED = "#app-.selected";
    private static final String MENU_SUB_OPTION = "ul>li";
    private static final String HEADER = "#main h1";

    private List<WebElement> getMenuOptions() {
        return driver.findElements(By.id(MENU_LIST));
    }

    private List<WebElement> getSubMenuItems(WebElement element) {
        return element.findElements(By.cssSelector(MENU_SUB_OPTION));
    }

    private WebElement getSelectedMenuOption() {
        return driver.findElement(By.cssSelector(MENU_OPTION_SELECTED));
    }

    public void checkMenu() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(MENU_BLOCK)));
        for (int index = 0; index < getMenuOptions().size(); index++) {
            WebElement menuOption = getMenuOptions().get(index);
            System.out.println("# " + menuOption.getText());
            menuOption.click();
            for (int subIndex = 0; subIndex < getSubMenuItems(getSelectedMenuOption()).size(); subIndex++) {
                WebElement subMenuOption = getSubMenuItems(getSelectedMenuOption()).get(subIndex);
                System.out.println("++ " + subMenuOption.getText());
                subMenuOption.click();
                assertTrue(isElementPresent(HEADER));
            }
        }
    }
}


