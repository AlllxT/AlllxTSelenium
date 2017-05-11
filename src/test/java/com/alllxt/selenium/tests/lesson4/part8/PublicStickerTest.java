package com.alllxt.selenium.tests.lesson4.part8;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by atribushny on 25.04.2017.
 */
public class PublicStickerTest extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
    }

    @Test
    public void checkStickers() {
        homePage.openHomePage().isHomePageLoaded();
        homePage.checkStickers();
    }

}
