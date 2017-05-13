package com.alllxt.selenium.tests.lesson7.part13;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.publicPages.Checkout;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.models.ModelBuilder.newUser;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 12.05.2017.
 */
public class PublicItemBuyTest extends BaseTest {
    private HomePage homePage;
    private Checkout checkout;
    private User user;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
        checkout = new Checkout();
        user = newUser();
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
