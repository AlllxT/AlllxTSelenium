package com.alllxt.selenium.litecart.pages.publicPages;

import com.alllxt.selenium.framework.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static com.alllxt.selenium.framework.utils.Tools.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class HomePage extends LiteCartBasicPage {

    private Checkout checkout = new Checkout();
    private Random randomGenerator = new Random();

    public static final String OPENED_TAB = "(//div[@class='tab-content']/div)[%d]";
    public static final String PRODUCTS_LOCATOR = "div.image-wrapper";
    public static final String STICKER_LOCATOR = "div.sticker";
    public static final String PRODUCT_TABS = ".nav.nav-tabs.nav-justified li";
    public static final String ACTIVE_TAB = PRODUCT_TABS + ".active";
    public static final String FULL_PAGE_LINK = "#view-full-page";
    public static final String BUTTON_CLOSE_PRODUCT = "button[aria-label='Close']";
    public static final String BASKET_LOCATOR = "#cart";

    public static final String TAB_COMMON_LOCATOR = "a[href='%s']";
    public static final String TAB_POPULAR_PRODUCTS = "#popular-products";
    public static final String TAB_CAMPAIGN_PRODUCTS = "#campaign-products";
    public static final String TAB_LATEST_PRODUCTS = "#latest-products";

    public static final String PRODUCT_IN_TAB = "div.product";

    public static final String PRODUCT_NAME_IN_CAMPAIGN_TAB = "#box-campaigns";
    public static final String PRODUCT_NAME_IN_POPULAR_TAB = "#box-most-popular";
    public static final String PRODUCT_NAME_IN_LATEST_TAB = "#box-latest-products";
    public static final String PRODUCT_NAME_IN_TAB = ".info .name";
    public static final String PRODUCT_OPENED = "#box-product";
    public static final String PRODUCT_NAME_OPENED = "#box-product .title";
    public static final String PRODUCT_SIZE = "[name='options[Size]']";
    public static final String ADD_TO_CART_BUTTON = "[name='add_cart_product']";
    public static final String PRICE_BOX = ".price-wrapper";
    public static final String REGULAR_PRICE = PRICE_BOX + "> .regular-price";
    public static final String CAMPAIGN_PRICE = PRICE_BOX + "> .campaign-price";
    private static final String BASKET_SIZE = "span.quantity";
    private static final String QUANTITY = "[name='quantity']";


    public HomePage openHomePage() {
        driver.get(BASEURL);
        return this;
    }

    public HomePage logout() {
        findElement(LOGOUT_LINK).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromString(LOGOUT_LINK)));
        return this;
    }

    public HomePage loginAs(User user) {
        loginAs(user.getEmail(), user.getPassword());
        return this;
    }

    public HomePage loginAs(String email, String pwd) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(LOGIN_EMAIL_FIELD)));
        findElement(LOGIN_EMAIL_FIELD).sendKeys(email);
        findElement(LOGIN_PASSWORD_FIELD).sendKeys(pwd);
        findElement(LOGIN_SIGN_IN_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromString(LOGIN_SIGN_IN_BUTTON)));
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

    public void openProductPage(WebElement element) {
        System.out.println("Opening product page");
        if (element.isDisplayed())
            element.click();
        wait.until(ExpectedConditions.stalenessOf(element));
        wait.until(visibilityOf(findElement(ADD_TO_CART_BUTTON)));
    }

    public void openFirstProduct() {
        openProductPage(findElement(TAB_CAMPAIGN_PRODUCTS + " " + PRODUCT_IN_TAB));
    }

    public void addProductToCart(int quantity) {
        if (isElementPresent(FULL_PAGE_LINK)) {
            findElement(FULL_PAGE_LINK).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(BASKET_SIZE)));
        String basketSizeOriginal = findElement(BASKET_SIZE).getText();
        findElement(QUANTITY).clear();
        findElement(QUANTITY).sendKeys(String.valueOf(quantity));
        wait.until(d -> d.findElement(getByFromString(QUANTITY)).getAttribute("value").equals(String.valueOf(quantity)));
        try {
            Select dropdown = new Select(findElement(PRODUCT_SIZE));
            dropdown.selectByValue("Small");
        } catch (NoSuchElementException e) {
            System.out.println("Unable to pick size. Option is not available");
        }
        findElement(ADD_TO_CART_BUTTON).click();
        wait.until(ExpectedConditions.textToBe(getByFromString(BASKET_SIZE), String.valueOf(Integer.parseInt(basketSizeOriginal) + quantity)));
    }

    public void switchToPopularProducts() {
        String popular = String.format(TAB_COMMON_LOCATOR, TAB_POPULAR_PRODUCTS);
        wait.until(ExpectedConditions.textToBe(getByFromString(popular), "Popular Products"));
        if (findElement(popular).isDisplayed()) {
            findElement(popular).click();
        }
    }

    public void chooseRandomProductFromList() {
        String tabId = findElement(ACTIVE_TAB).findElement(getByFromString("a")).getAttribute("href").split("#")[1];
        List<WebElement> productList = findElement("#" + tabId)
                .findElements(getByFromString(PRODUCT_IN_TAB));
        int index = randomGenerator.nextInt(productList.size());
        wait.until(ExpectedConditions.elementToBeClickable(productList.get(index)));
        productList.get(index).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(ADD_TO_CART_BUTTON)));
    }

    public void openBasket() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(BASKET_LOCATOR)));
        findElement(BASKET_LOCATOR).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromString(BASKET_LOCATOR)));
    }


}
