package com.alllxt.selenium.framework.utils;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.bases.BasePage.paintElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by atribushny on 10.05.2017.
 */
public class Tools {

    static WebDriver driver = LocalDriverManager.getDriver();

    public static boolean titleIsPresent(final WebDriverWait wait, final String title) {
        return wait.until(titleIs(title));
    }

    public static boolean isCollectionsEqual(ArrayList<String> originalList, TreeSet<String> sortedList) {
        if (originalList.size() == sortedList.size()) {
            System.out.println("\n Original list: == Sorted list:");
            for (int i = 0; i < originalList.size(); i++) {
                System.out.println(originalList.get(i) + " == " + new ArrayList<>(sortedList).get(i));
            }
        }
        return originalList.equals(new ArrayList<>(sortedList));
    }

    public static void click(WebElement element) {
        paintElement(element, "green");
        element.click();
    }



    public static void click(String locator) {
        if (locator.startsWith("//")
                || locator.startsWith(".//")
                || locator.startsWith("./")) {
            driver.findElement(By.xpath(locator)).click();
        } else {
            driver.findElement(By.cssSelector(locator)).click();
        }
    }

}
