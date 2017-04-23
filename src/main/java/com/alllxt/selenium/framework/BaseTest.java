package com.alllxt.selenium.framework;

/**
 * Created by atribushny on 23.04.2017.
 */
public class BaseTest {
    protected static final String BASEURL = "http://localhost/litecart/admin/";
    protected static final int TIMEOUT = 10;

    protected static final String USER_NAME_TEXT_FIELD_CSS_LOCATOR = "[name='username']"; // or By.name
    protected static final String USER_PWD_TEXT_FIELD_CSS_LOCATOR = "[name='password']"; // or By.name
    protected static final String USER_LOGIN_BUTTON_CSS_LOCATOR = "button[name='login']";
    protected static final String LOGOUT_BUTTON_CSS_LOCATOR = "a[title='Logout']";
    protected static final String REMEMBER_ME_CHECKBOX = "input[name='remember_me']"; // or By.name

}
