package com.alllxt.selenium.litecart.pages.publicPages;

import com.alllxt.selenium.framework.bases.BasePage;

import static com.alllxt.selenium.framework.utils.Tools.findElement;

/**
 * Created by atribushny on 12.05.2017.
 */
public class LiteCartBasicPage extends BasePage {
    protected static final String REGISTER_LINK = "//a[.='New customers click here']";
    protected static final String LOGOUT_LINK = "//div[@id='box-account']//a[.='Logout']";
    protected static final String LOGIN_EMAIL_FIELD = "[name='login_form'] [name='email']";
    protected static final String LOGIN_PASSWORD_FIELD = "[name='login_form'] [name='password']";
    protected static final String LOGIN_SIGN_IN_BUTTON = "button[name='login']";


    public CreateAccountPage clickRegisterNewUser() {
        findElement(REGISTER_LINK).click();
        return new CreateAccountPage();
    }
}
