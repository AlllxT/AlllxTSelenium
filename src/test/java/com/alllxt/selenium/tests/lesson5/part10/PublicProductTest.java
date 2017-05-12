package com.alllxt.selenium.tests.lesson5.part10;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static com.alllxt.selenium.framework.utils.Tools.findElement;
import static com.alllxt.selenium.framework.utils.Tools.findElementInElement;
import static com.alllxt.selenium.litecart.pages.publicPages.HomePage.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class PublicProductTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
    }

    @Test
    public void checkDucks() {
        homePage.openHomePage();
        assertTrue(homePage.isHomePageLoaded());

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

        homePage.verifyDecoration(productInTab);
        homePage.verifyRegularPriceColor(productInTab);
        homePage.verifyCampaignPriceWeight(productInTab);
        homePage.verifyCampaignPriceColor(productInTab);
        homePage.verifyFontSizes(productInTab);

        System.out.println(separator);
        homePage.openProductPage(productInTab);
        System.out.println(separator);

        //opened product
        WebElement productOpened = findElement(PRODUCT_OPENED);
        productNameOpened = findElementInElement(productOpened, PRODUCT_NAME_OPENED).getText();
        productPriceOpenedRegular = findElementInElement(productOpened, REGULAR_PRICE).getText();
        productPriceOpenedCampaign = findElementInElement(productOpened, CAMPAIGN_PRICE).getText();

        System.out.println("Product name: " + productNameOpened);
        System.out.println("Product regular price: " + productPriceOpenedRegular);
        System.out.println("Product campaign price: " + productPriceOpenedCampaign);

        homePage.verifyDecoration(productOpened);
        homePage.verifyRegularPriceColor(productOpened);
        homePage.verifyCampaignPriceWeight(productOpened);
        homePage.verifyCampaignPriceColor(productOpened);
        homePage.verifyFontSizes(productOpened);

        System.out.println(separator);
        assertEquals(productNameInTab, productNameOpened);
        System.out.println("Are product names equal: " + (Objects.equals(productNameInTab, productNameOpened)));
        assertEquals(productPriceInTabRegular, productPriceOpenedRegular);
        System.out.println("Are product regular prices equal: " + (Objects.equals(productPriceInTabRegular, productPriceOpenedRegular)));
        assertEquals(productPriceInTabCampaign, productPriceOpenedCampaign);
        System.out.println("Are product Campaign prices equal: " + (Objects.equals(productPriceInTabCampaign, productPriceOpenedCampaign)));

    }

}
