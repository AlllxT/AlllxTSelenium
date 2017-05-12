package com.alllxt.selenium.framework.models;

import static org.apache.commons.lang.RandomStringUtils.*;

public class ModelBuilder {

    public static User newUser() {
        User user = new User();
        user.setFname(randomAlphabetic(5));
        user.setLname(randomAlphabetic(8));
        user.setEmail(randomAlphabetic(5) + "@mail.mail");
        user.setPassword(randomAlphabetic(5));
        user.setAdress(randomAlphabetic(10) + " " + randomNumeric(2));
        return user;
    }

    public static Product newProduct() {
        Product product = new Product();
        product.setCode(randomNumeric(8));
        product.setDescription(randomAlphabetic(50));
        product.setPriceUSD(random(2, 50, 51, false, true));
        product.setName(randomAlphabetic(10));
        product.setShortDescription(randomAlphabetic(20));
        product.setQuantity(random(2, 11, 99, false, true));
        return product;
    }


}
