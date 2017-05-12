package com.alllxt.selenium.tests.lesson6.part11;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.publicPages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.models.UserBuilder.newUser;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 12.05.2017.
 */
public class PublicRegisterNewUserTest extends BaseTest {

    private HomePage homePage;
    private User user;

    @BeforeMethod
    public void setUpTestClass() {
        homePage = new HomePage();
        user = newUser();
    }

    @Test
    public void registerNewUser() {
        homePage.openHomePage();
        assertTrue(homePage.isHomePageLoaded());
        homePage.clickRegiterNewUser()
                .doRegistration(user);
        assertTrue(homePage.isHomePageLoaded());
        homePage.logout()
                .loginAs(user)
                .logout();
    }

}
