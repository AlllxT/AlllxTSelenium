package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages;

import com.alllxt.selenium.framework.models.Product;
import com.alllxt.selenium.framework.utils.Tools;
import com.alllxt.selenium.litecart.pages.adminPages.LitecartBasicPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.*;

/**
 * Created by atribushny on 12.05.2017.
 */
public class Catalog extends LitecartBasicPage {

    private static final String CATALOG_TITLE = "//h1[contains(.,' Catalog')]";
    private static final String ADD_NEW_PRODUCT = "//a[.=' Add New Product']";
    private static final String PRODUCT_COMMON_LINK = "//a[.='%s']";
    private static final String FOLDER_CLOSED = ".fa.fa-folder";
    private static final String FOLDER_OPEN = ".fa.fa-folder-open";

    public void clickAddNewProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        findElement(ADD_NEW_PRODUCT).click();
    }

    public boolean isNewProductAdded(Product product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        String productNameLink = String.format(PRODUCT_COMMON_LINK, product.getName());
        return findElement(productNameLink).isDisplayed();
    }

    public boolean isCatalogOpened(){
        findElement(CATALOG_TITLE);
        return true;
    }

    public void openAllFolders(){
        if (findElements(FOLDER_CLOSED).size()>0){
            findElements(FOLDER_CLOSED).forEach(folder -> click(FOLDER_CLOSED+" a"));
        }

    }

}
