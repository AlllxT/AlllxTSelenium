package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages;

import static com.alllxt.selenium.framework.utils.Tools.findElement;

/**
 * Created by atribushny on 22.05.2017.
 */
public class EditProductPage extends Catalog {
    private static final String PRODUCT_CANCEL_BUTTON = "[name='cancel']";

    public void clickCancel(){
        findElement(PRODUCT_CANCEL_BUTTON).click();
    }
}
