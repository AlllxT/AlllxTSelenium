package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages;

import com.alllxt.selenium.framework.configuration.ConfigUtils;
import com.alllxt.selenium.framework.models.Product;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static com.alllxt.selenium.framework.utils.Tools.findElement;
import static com.alllxt.selenium.framework.utils.Tools.getByFromString;

/**
 * Created by atribushny on 12.05.2017.
 */
public class AddNewProductPage extends Catalog {

    private static final String IMAGE_FILE_PATH = ConfigUtils.getNewProductImageFilePath();

    private static final String PRODUCT_NAME_FIELD = "[name='name[en]']";
    private static final String PRODUCT_CODE_FIELD = "[name='code']";
    private static final String PRODUCT_QUANTITY_FIELD = "[name='quantity']";
    private static final String PRODUCT_SHORT_DESCRIPTION_FIELD = "[name='short_description[en]']";
    private static final String PRODUCT_DESCRIPTION_FIELD = "div.trumbowyg-editor";
    private static final String PRODUCT_PIRCE_USD_FIELD = "[name='prices[USD]']";
    private static final String PRODUCT_SAVE_BUTTON = "[name='save']";
    private static final String PRODUCT_IMAGE_UPLOAD_BUTTON = "[name='new_images[]']";

    private static final String ENABLE_STATUS_PRODUCT = "//div//*[.=' Enabled']";

    private static final String TAB_COMMON_LOCATOR = "//a[.='%s']";
    private static final String GENERAL_TAB_NAME = "General";
    private static final String INFORMATION_TAB_NAME = "Information";
    private static final String PRICE_TAB_NAME = "Prices";
    private static final String OPTIONS_TAB_NAME = "Options";

    private void openTab(String tabName) {
        String tab = String.format(TAB_COMMON_LOCATOR, tabName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(tab)));
        findElement(tab).click();
    }

    private void enableProduct() {
        findElement(ENABLE_STATUS_PRODUCT).click();
    }

    private void uploadProductImage() {
        String imageAbsolutePath = new File(IMAGE_FILE_PATH).getAbsoluteFile().toString();
        findElement(PRODUCT_IMAGE_UPLOAD_BUTTON).sendKeys(imageAbsolutePath);
    }

    public void addNewProductSimple(Product product) {
        openTab(GENERAL_TAB_NAME);
        enableProduct();
        uploadProductImage();
        fillProductName(product.getName());
        fillProductCode(product.getCode());
        fillQuantity(product.getQuantity());
        openTab(INFORMATION_TAB_NAME);
        fillShortDesc(product.getShortDescription());
        fillDesc(product.getDescription());
        openTab(PRICE_TAB_NAME);
        fillPriceUSD(product.getPriceUSD());
        saveProduct();
    }

    private void saveProduct() {
        findElement(PRODUCT_SAVE_BUTTON).click();
    }

    private void fillProductName(String name) {
        findElement(PRODUCT_NAME_FIELD).sendKeys(name);
    }

    private void fillProductCode(String code) {
        findElement(PRODUCT_CODE_FIELD).sendKeys(code);
    }

    private void fillShortDesc(String shortDescription) {
        findElement(PRODUCT_SHORT_DESCRIPTION_FIELD).sendKeys(shortDescription);
    }

    private void fillDesc(String description) {
        findElement(PRODUCT_DESCRIPTION_FIELD).sendKeys(description);
    }

    private void fillQuantity(String quantity) {
        findElement(PRODUCT_QUANTITY_FIELD).clear();
        findElement(PRODUCT_QUANTITY_FIELD).sendKeys(quantity);
    }

    private void fillPriceUSD(String priceUSD) {
        findElement(PRODUCT_PIRCE_USD_FIELD).sendKeys(priceUSD);
    }
}
