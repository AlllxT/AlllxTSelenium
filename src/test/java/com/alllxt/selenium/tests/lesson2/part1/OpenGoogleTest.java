package com.alllxt.selenium.tests.lesson2.part1;

import com.alllxt.selenium.framework.bases.BaseTest;
import org.testng.annotations.Test;


public class OpenGoogleTest extends BaseTest {

    @Test
    public void openGoogleTest() {
        driver.get("http://www.google.com");
    }

}
