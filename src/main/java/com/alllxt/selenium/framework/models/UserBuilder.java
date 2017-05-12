package com.alllxt.selenium.framework.models;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

public class UserBuilder {
    public static User newUser() {
        User user = new User();
        user.setFname(randomAlphabetic(5));
        user.setLname(randomAlphabetic(8));
        user.setEmail(randomAlphabetic(5) + "@mail.mail");
        user.setPassword(randomAlphabetic(5));
        user.setAdress(randomAlphabetic(10)+" "+randomNumeric(2));
        return user;
    }
}
