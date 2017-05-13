package com.alllxt.selenium.litecart.pages.publicPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.*;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 13.05.2017.
 */
public class Checkout extends LiteCartBasicPage {

    private static final String REMOVE_PRODUCT_BUTTONS = "[name='remove_cart_item']";
    private static final String EMPTY_BASKET = "//*[.='There are no items in your cart.']";
    private static final String LOADER = ".loader";


    public void removeAllProducts() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByFromString(REMOVE_PRODUCT_BUTTONS)));
        for (int index = 0; index <= findElements(REMOVE_PRODUCT_BUTTONS).size(); index++) {
            WebElement element = findElement(REMOVE_PRODUCT_BUTTONS);
            element.click();
            wait.until(ExpectedConditions.stalenessOf(element));
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(EMPTY_BASKET)));
        assertTrue(isElementPresent(EMPTY_BASKET));
    }

}
