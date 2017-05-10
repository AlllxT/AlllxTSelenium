package com.alllxt.selenium.tests.lesson4.part7;

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
public class AdminMenuTest extends BaseTest {

    private User user = new User();
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;

    private String userName = user.getAdminUserName();
    private String userPassword = user.getAdminUserName();


    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
    }

    @Test
    public void checkMenuItem() {
        adminLoginPage.openAdminLoginPage()
                .isLoginPageLoaded();
        adminLoginPage.enterUsername(userName)
                .enterPassword(userPassword).submitLogin();
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        adminHomePage.checkMenu();
    }

}
