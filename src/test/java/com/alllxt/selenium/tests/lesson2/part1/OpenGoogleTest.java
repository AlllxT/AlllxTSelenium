package com.alllxt.selenium.tests.lesson2.part1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by atribushny on 18.04.2017.
 */
public class OpenGoogleTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void openGoogleTest() {
        driver.get("http://www.google.com");
    }

    @AfterTest
    public void tearDrown() {
        driver.quit();
    }
}
