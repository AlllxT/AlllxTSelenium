package com.alllxt.selenium.tests.lesson11.part19;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.litecart.pages.publicPages.Checkout;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 12.05.2017.
 */
public class PublicItemBuyTest extends BaseTest {
    private HomePage homePage;
    private Checkout checkout;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
        checkout = new Checkout();
    }

    @Test
    public void itemBuy() {
        homePage.openHomePage();
        assertTrue(homePage.isHomePageLoaded());
        homePage.openFirstProduct();
        homePage.addProductToCart(1);
        homePage.openHomePage();
        homePage.switchToPopularProducts();
        homePage.chooseRandomProductFromList();
        homePage.addProductToCart(2);
        homePage.openBasket();
        checkout.removeAllProducts();
    }

}
