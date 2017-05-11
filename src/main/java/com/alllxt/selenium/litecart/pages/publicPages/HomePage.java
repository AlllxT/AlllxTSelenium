package com.alllxt.selenium.litecart.pages.publicPages;

import com.alllxt.selenium.framework.bases.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Objects;

import static com.alllxt.selenium.framework.utils.Tools.*;
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

    private static final String PRODUCT_IN_TAB = "#campaign-products div.product";
    private static final String PRODUCT_NAME_IN_TAB = "#box-campaigns .info .name";

    private static final String PRODUCT_OPENED = "#box-product";
    private static final String PRODUCT_NAME_OPENED = "#box-product .title";

    private static final String ADD_TO_CART_BUTTON = "[name='buy_now_form'] [name='add_cart_product']";

    private static final String PRICE_BOX = ".price-wrapper";
    private static final String REGULAR_PRICE = PRICE_BOX + "> .regular-price";
    private static final String CAMPAIGN_PRICE = PRICE_BOX + "> .campaign-price";


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

    public void checkStickersPresence() {
        List<WebElement> tabs = driver.findElements(By.cssSelector(PRODUCT_TABS));
        for (int tab = 0; tab < tabs.size(); tab++) {
            List<WebElement> productList = null;
            System.out.println("# " + tabs.get(tab).getText() + " tab is opened");
            click(tabs.get(tab));
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

    public void verifyDecoration(WebElement element) {
        String regularPriceDecoration = getTextDecorationOfElement(element, REGULAR_PRICE);
        assertEquals(regularPriceDecoration, "line-through", "Regular price should be with line-through");
        System.out.println("Regular price decoration is: " + regularPriceDecoration);
    }

    public void verifyRegularPriceColor(WebElement element) {
        String regularPriceColor = getColorOfElement(element, REGULAR_PRICE);
        assertEquals(regularPriceColor, "rgba(51, 51, 51, 1)", "Regular price color should be dark-grey");
        System.out.println("Regular price color is: " + regularPriceColor);
    }

    public void verifyCampaignPriceColor(WebElement element) {
        String campaignPriceColor = getColorOfElement(element, CAMPAIGN_PRICE);
        assertEquals(campaignPriceColor, "rgba(204, 0, 0, 1)", "Campaign price should be red");
        System.out.println("Campaign price color is: " + campaignPriceColor);
    }

    public void verifyCampaignPriceWeight(WebElement element) {
        String campaignPriceWeight = getFontWeightOfElement(element, CAMPAIGN_PRICE);
        assertEquals(campaignPriceWeight, "bold", "Campaign price should be bold");
        System.out.println("Campaign price weight is: " + campaignPriceWeight);
    }

    public void verifyFontSizes(WebElement element) {
        float regularFontSize = getFontSizeOfElement(element, REGULAR_PRICE);
        float campaignFontSize = getFontSizeOfElement(element, CAMPAIGN_PRICE);
        assertTrue(regularFontSize < campaignFontSize, "Regular price should be less than campaign");
        System.out.println("Regular price size is: " + regularFontSize);
        System.out.println("Campaign price size is: " + campaignFontSize);
        System.out.println("Is Campaign price bigger than Regular price inside tab: " + (regularFontSize < campaignFontSize));
    }


    public void checkProduct() {
        String productNameInTab, productPriceInTabRegular, productPriceInTabCampaign;
        String productNameOpened, productPriceOpenedRegular, productPriceOpenedCampaign;

        //tab
        WebElement productInTab = findElement(PRODUCT_IN_TAB);
        productNameInTab = findElementInElement(productInTab, PRODUCT_NAME_IN_TAB).getText();
        productPriceInTabRegular = findElementInElement(productInTab, REGULAR_PRICE).getText();
        productPriceInTabCampaign = findElementInElement(productInTab, CAMPAIGN_PRICE).getText();

        String separator = "---------";
        System.out.println(separator);
        System.out.println("Product name: " + productNameInTab);
        System.out.println("Product regular price: " + productPriceInTabRegular);
        System.out.println("Product campaign price: " + productPriceInTabCampaign);

        verifyDecoration(productInTab);
        verifyRegularPriceColor(productInTab);
        verifyCampaignPriceWeight(productInTab);
        verifyCampaignPriceColor(productInTab);
        verifyFontSizes(productInTab);

        System.out.println(separator);
        openProductPage(productInTab);
        System.out.println(separator);

        //opened product
        WebElement productOpened = findElement(PRODUCT_OPENED);
        productNameOpened = findElementInElement(productOpened, PRODUCT_NAME_OPENED).getText();
        productPriceOpenedRegular = findElementInElement(productOpened, REGULAR_PRICE).getText();
        productPriceOpenedCampaign = findElementInElement(productOpened, CAMPAIGN_PRICE).getText();

        System.out.println("Product name: " + productNameOpened);
        System.out.println("Product regular price: " + productPriceOpenedRegular);
        System.out.println("Product campaign price: " + productPriceOpenedCampaign);

        verifyDecoration(productOpened);
        verifyRegularPriceColor(productOpened);
        verifyCampaignPriceWeight(productOpened);
        verifyCampaignPriceColor(productOpened);
        verifyFontSizes(productOpened);

        System.out.println(separator);
        assertEquals(productNameInTab, productNameOpened);
        System.out.println("Are product names equal: " + (Objects.equals(productNameInTab, productNameOpened)));
        assertEquals(productPriceInTabRegular, productPriceOpenedRegular);
        System.out.println("Are product regular prices equal: " + (Objects.equals(productPriceInTabRegular, productPriceOpenedRegular)));
        assertEquals(productPriceInTabCampaign, productPriceOpenedCampaign);
        System.out.println("Are product Campaign prices equal: " + (Objects.equals(productPriceInTabCampaign, productPriceOpenedCampaign)));

    }

    private void openProductPage(WebElement element) {
        System.out.println("Opening product page");
        if(element.isDisplayed())
        element.click();
        wait.until(ExpectedConditions.stalenessOf(element));
        wait.until(ExpectedConditions.visibilityOf(findByCss(ADD_TO_CART_BUTTON)));
    }


}
