package com.alllxt.selenium.litecart.pages.adminPages;

import com.alllxt.selenium.framework.models.User;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.alllxt.selenium.framework.utils.Tools.click;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

/**
 * Created by atribushny on 24.04.2017.
 */
public class AdminLoginPage extends LitecartBasicPage {

    protected static final String USER_NAME_TEXT_FIELD_CSS_LOCATOR = "[name='username']"; // or By.name
    protected static final String USER_PWD_TEXT_FIELD_CSS_LOCATOR = "[name='password']"; // or By.name
    protected static final String USER_LOGIN_BUTTON_CSS_LOCATOR = "button[name='login']";
    protected static final String REMEMBER_ME_CHECKBOX = "input[name='remember_me']"; // or By.name

    public AdminLoginPage openAdminLoginPage() {
        driver.get(ADMIN_BASEURL);
        return this;
    }

    public void openAndLogin(User user) {
        openAdminLoginPage()
                .isLoginPageLoaded();
        enterUsername(user.getAdminUserName())
                .enterPassword(user.getAdminUserPassword())
                .submitLogin();
        wait.until(invisibilityOf(findByCss(USER_LOGIN_BUTTON_CSS_LOCATOR)));
        wait.until(ExpectedConditions.urlToBe(ADMIN_BASEURL));
    }


    public boolean isLoginPageLoaded() {
        return isElementPresent("#box-login");
    }

    public AdminLoginPage enterUsername(String username) {
        findByCss(USER_NAME_TEXT_FIELD_CSS_LOCATOR).clear();
        findByCss(USER_NAME_TEXT_FIELD_CSS_LOCATOR).sendKeys(username);
        return this;
    }

    public AdminLoginPage enterPassword(String password) {
        findByCss(USER_PWD_TEXT_FIELD_CSS_LOCATOR).clear();
        findByCss(USER_PWD_TEXT_FIELD_CSS_LOCATOR).sendKeys(password);
        return this;
    }

    public void submitLogin() {
        click(USER_LOGIN_BUTTON_CSS_LOCATOR);
    }


}
