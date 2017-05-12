package com.alllxt.selenium.litecart.pages.publicPages;

import com.alllxt.selenium.framework.models.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.alllxt.selenium.framework.utils.Tools.click;
import static com.alllxt.selenium.framework.utils.Tools.findElement;
import static com.alllxt.selenium.framework.utils.Tools.getByFromString;

/**
 * Created by atribushny on 12.05.2017.
 */
public class CreateAccountPage extends LiteCartBasicPage {

    private static final String FIRST_NAME_FIELD = "[name='firstname']";
    private static final String LAST_NAME_FIELD = "[name='lastname']";
    private static final String ADRESS_FIELD = "[name='address1']";
    private static final String COUNTRY_DROPDOWM = "[name='country_code']";
    private static final String EMAIL_FIELD = "[name='customer_form'] [name='email']";
    private static final String PASSWORD_FIELD = "[name='customer_form'] [name='password']";
    private static final String PASSWORD_CONFIRM_FIELD = "[name='customer_form'] [name='confirmed_password']";
    private static final String CREATE_ACCOUNT_BUTTON = "button[name='create_account']";


    private CreateAccountPage fillFirstName(String fname) {
        findElement(FIRST_NAME_FIELD).sendKeys(fname);
        return this;
    }

    private CreateAccountPage fillLastName(String lname) {
        findElement(LAST_NAME_FIELD).sendKeys(lname);
        return this;
    }

    private CreateAccountPage fillAdress(String adress) {
        findElement(ADRESS_FIELD).sendKeys(adress);
        return this;
    }

    private CreateAccountPage fillEmail(String email) {
        findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    private CreateAccountPage fillPassword(String password) {
        fillDesiredPassword(password);
        fillConfirmPassword(password);
        return this;
    }

    private void fillDesiredPassword(String password) {
        findElement(PASSWORD_FIELD).sendKeys(password);
    }

    private void fillConfirmPassword(String password) {
        findElement(PASSWORD_CONFIRM_FIELD).sendKeys(password);
    }

    public HomePage doRegistration(User user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getByFromString(CREATE_ACCOUNT_BUTTON)));
        chooseCountry();
        fillFirstName(user.getFname())
                .fillLastName(user.getLname())
                .fillAdress(user.getAdress())
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword());
        submitRegistration();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getByFromString(CREATE_ACCOUNT_BUTTON)));
        return new HomePage();
    }

    private void chooseCountry() {
        Select country = new Select(findElement(COUNTRY_DROPDOWM));
        country.selectByValue("US");
    }

    private void submitRegistration() {
        findElement(CREATE_ACCOUNT_BUTTON).click();
    }



}
