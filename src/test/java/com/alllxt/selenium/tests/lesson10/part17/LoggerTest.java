package com.alllxt.selenium.tests.lesson10.part17;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import com.alllxt.selenium.litecart.pages.adminPages.AdminLoginPage;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages.Catalog;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.utils.Tools.titleIsPresent;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 15.05.2017.
 */
public class LoggerTest extends BaseTest {

    private User user = new User();
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;
    private Catalog catalog;


    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
        catalog = new Catalog();
    }

    @Test(enabled = true)
    public void loggerTest() {
        adminLoginPage.openAndLogin(user);
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        assertTrue(catalog.isCatalogOpened());
        catalog.openAllFolders();
        catalog.logMsgVerify();
        adminHomePage.logout();
    }
}
