package com.alllxt.selenium.tests.lesson6.part12;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.Product;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import com.alllxt.selenium.litecart.pages.adminPages.AdminLoginPage;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages.AddNewProductPage;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.catalog.pages.Catalog;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.models.ModelBuilder.newProduct;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 12.05.2017.
 */
public class AdminAddNewProductTest extends BaseTest {

    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;
    private Catalog catalog;
    private AddNewProductPage addNewProductPage;
    private User user;
    private Product product;

    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
        catalog = new Catalog();
        addNewProductPage = new AddNewProductPage();
        user = new User();
        product = newProduct();
    }

    @Test
    public void registerNewUser() {
        adminLoginPage.openAndLogin(user);
        adminHomePage.openCatalog();
        catalog.clickAddNewProduct();
        addNewProductPage.addNewProductSimple(product);
        assertTrue(catalog.isNewProductAdded(product));
    }
}
