package com.alllxt.selenium.tests.lesson8.part14;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import com.alllxt.selenium.litecart.pages.adminPages.AdminLoginPage;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries.Countries;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries.EditCountries;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.utils.Tools.titleIsPresent;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 15.05.2017.
 */
public class newWindowsTest extends BaseTest {

    private User user = new User();
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;
    private Countries adminCountries;
    private EditCountries editCountries;


    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
        adminCountries = new Countries();
        editCountries = new EditCountries();
    }

    @Test(enabled = true)
    public void newWindowsTest() {
        adminLoginPage.openAndLogin(user);
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        adminHomePage.openCountries();
        adminCountries.openRandomCountryToEdit();
        editCountries.externalLinkVerify();
        adminHomePage.logout();
    }
}
