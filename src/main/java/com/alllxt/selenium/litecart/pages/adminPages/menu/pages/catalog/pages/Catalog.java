package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages;

import com.alllxt.selenium.framework.models.Product;
import com.alllxt.selenium.litecart.pages.adminPages.LitecartBasicPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.*;
import static org.testng.Assert.assertFalse;

/**
 * Created by atribushny on 12.05.2017.
 */
public class Catalog extends LitecartBasicPage {

    private static final String CATALOG_TITLE = "//h1[contains(.,' Catalog')]";
    private static final String ADD_NEW_PRODUCT = "//a[.=' Add New Product']";
    private static final String PRODUCT_COMMON_LINK = "//a[.='%s']";
    private static final String FOLDER_CLOSED = ".fa.fa-folder";
    private static final String FOLDER_OPEN = ".fa.fa-folder-open";
    private static final String CATALOG_ROW = "form[name=catalog_form] tr";
    private static final String PRODUCT_IMAGE = CATALOG_ROW + " img";


    public void clickAddNewProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        findElement(ADD_NEW_PRODUCT).click();
    }

    public boolean isNewProductAdded(Product product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_NEW_PRODUCT)));
        String productNameLink = String.format(PRODUCT_COMMON_LINK, product.getName());
        return findElement(productNameLink).isDisplayed();
    }

    public boolean isCatalogOpened() {
        findElement(CATALOG_TITLE);
        return true;
    }


    public void openAllFolders() {
        boolean condition = true;
        while (condition) {
            condition = false;
            for (int index = 0; index < findElements(CATALOG_ROW).size(); index++) {
                WebElement row = findElements(CATALOG_ROW).get(index);
                if (isElementPresentInElement(row, FOLDER_CLOSED)) {
                    findElementInElement(row, FOLDER_CLOSED + " + a").click();
                    condition = true;
                    break;
                }
            }
        }
    }

    private boolean isErrorPresent(LogEntries entries) {
        for (LogEntry entry : entries.getAll()) {
            if (entry.getLevel().getName().equals("SEVERE") ||
                    entry.getLevel().getName().equals("WARNING")) {
                return true;
            }
        }
        return false;
    }

    public void logMsgVerify() {
        for (int index = 0; index < findElements(PRODUCT_IMAGE).size(); index++) {
            findElements(PRODUCT_IMAGE + " + a").get(index).click();
            assertFalse(isErrorPresent(driver.manage().logs().get("browser")));
            new EditProductPage().clickCancel();
            isCatalogOpened();
        }

    }
}
