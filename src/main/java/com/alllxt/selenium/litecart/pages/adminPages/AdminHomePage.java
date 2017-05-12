package com.alllxt.selenium.litecart.pages.adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.click;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class AdminHomePage extends LitecartBasicPage {


    public boolean isHomePageLoaded() {
        return wait.until(ExpectedConditions.urlToBe(ADMIN_BASEURL));
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


