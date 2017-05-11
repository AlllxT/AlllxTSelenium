package com.alllxt.selenium.tests.lesson5.part10;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 25.04.2017.
 */
public class PublicStickerPriceTest extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
    }

    @Test
    public void checkDucks() {
        homePage.openHomePage();
        assertTrue(homePage.isHomePageLoaded());
        homePage.checkProduct();

    }

}
