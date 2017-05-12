package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages;

import com.alllxt.selenium.framework.models.Product;
import com.alllxt.selenium.litecart.pages.adminPages.LitecartBasicPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.findElement;
import static com.alllxt.selenium.framework.utils.Tools.getByFromString;

/**
 * Created by atribushny on 12.05.2017.
 */
public class Catalog extends LitecartBasicPage {

    private static final String ADD_NEW_PRODUCT = "//a[.=' Add New Product']";
    private static final String PRODUCT_COMMON_LINK = "//a[.='%s']";

    public void clickAddNewProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        findElement(ADD_NEW_PRODUCT).click();
    }

    public boolean isNewProductAdded(Product product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        String productNameLink = String.format(PRODUCT_COMMON_LINK, product.getName());
        return findElement(productNameLink).isDisplayed();
    }

}
