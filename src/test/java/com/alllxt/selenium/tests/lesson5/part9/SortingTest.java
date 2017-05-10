package com.alllxt.selenium.tests.lesson5.part9;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import com.alllxt.selenium.litecart.pages.adminPages.AdminLoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.utils.Tools.titleIsPresent;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 25.04.2017.
 */
public class SortingTest extends BaseTest {

    private User user = new User();
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;


    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
    }

    @Test
    public void checkMenuItem() {
        adminLoginPage.openAndLogin(user);
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        adminHomePage.checkMenu();
    }

}
