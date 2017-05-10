package com.alllxt.selenium.litecart.pages.publicPages;

import com.alllxt.selenium.framework.bases.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class HomePage extends BasePage {

    private static final String OPENED_TAB = "(//div[@class='tab-content']/div)[%d]";
    private static final String PRODUCTS_LOCATOR = "div.image-wrapper";
    private static final String STICKER_LOCATOR = "div.sticker";
    private static final String PRODUCT_TABS = ".nav.nav-tabs.nav-justified li";


    public HomePage openHomePage() {
        driver.get(BASEURL);
        return this;
    }

    public boolean isHomePageLoaded() {
        return isElementPresent(PRODUCTS_LOCATOR);
    }

    public boolean isStickerPresent(WebElement element) {
        return element.findElements(By.cssSelector(STICKER_LOCATOR)).size() > 0;
    }

    public void checkStickers() {
        List<WebElement> tabs = driver.findElements(By.cssSelector(PRODUCT_TABS));
        for (int tab = 0; tab < tabs.size(); tab++) {
            List<WebElement> productList = null;
            System.out.println("# " + tabs.get(tab).getText() + " tab is opened");
            tabs.get(tab).click();
            WebElement currentProducts = driver.findElement(By.xpath(String.format(OPENED_TAB, tab + 1)));
            productList = currentProducts.findElements(By.cssSelector(PRODUCTS_LOCATOR));
            for (WebElement product : productList) {
                assertTrue(isStickerPresent(product));
                int numberStickers = product.findElements(By.cssSelector(STICKER_LOCATOR)).size();
                assertEquals(numberStickers, 1, "Number of stickers should equals 1");
                System.out.println("Sticker " + product.findElement(By.cssSelector(STICKER_LOCATOR)).getText() + " is found.");
            }
        }
    }


}
