package com.alllxt.selenium.framework.utils;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

/**
 * Created by atribushny on 10.05.2017.
 */
public class Tools {
    private static WebDriverWait wait;


    public Tools(WebDriverWait wait) {
        this.wait = wait;
    }

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
        if (element.isDisplayed()) {
            element.click();
        }
    }

    public static void click(String locator) {
        click(findElement(locator));
    }

    public static By getByFromString(String stringLocator) {
        if (stringLocator.startsWith("//")
                || stringLocator.startsWith(".//")
                || stringLocator.startsWith("(//")
                || stringLocator.startsWith("(.//")
                || stringLocator.startsWith("/*//")
                || stringLocator.startsWith("(./*")
                || stringLocator.startsWith("((//")) {
            return By.xpath(stringLocator);
        } else {
            return By.cssSelector(stringLocator);
        }
    }

    //todo
    public static WebElement findElement(String locator) {
        return findElement(getByFromString(locator), ExpectedConditions::presenceOfElementLocated, 60);
    }

    public static WebElement findElement(By locator, final Function<By, ExpectedCondition<WebElement>> condition, Integer timeout) {
        int defaultTimeout = 60;
        WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), defaultTimeout);
        return wait.withTimeout(
                Optional.ofNullable(timeout)
                        .filter(value -> value >= 0)
                        .orElse(defaultTimeout), TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(WebDriverException.class)
                .until(condition.apply(locator));
    }


    public static List<WebElement> findElements(String locator) {
        wait.until(visibilityOfAllElementsLocatedBy(getByFromString(locator)));
        return driver.findElements(getByFromString(locator));
    }

    public static List<WebElement> findElementsInElement(WebElement inElement, String locator) {
        return inElement.findElements(getByFromString(locator));
    }

    public static WebElement findElementInElement(WebElement inElement, String locator) {
        WebElement element = inElement.findElement(getByFromString(locator));
        element.isDisplayed();
        return element;
    }

    public static WebElement findElementInElement(WebElement inElement, By locator) {
        return inElement.findElement(locator);
    }


    public static String getAttributeOfElement(String locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    public static String getAttributeOfElement(WebElement element, String locator, String attribute) {
        return findElementInElement(element, locator).getAttribute(attribute);
    }


    public static String getColorOfElement(String locator) {
        String color = findElement(locator).getCssValue("color");
        return Color.fromString(color).asRgba();
    }

    public static String getColorOfElement(WebElement element, String locator) {
        String color = findElementInElement(element, locator).getCssValue("color");
        return Color.fromString(color).asRgba();
    }


    public static float getFontSizeOfElement(String locator) {
        return Float.parseFloat(findElement(locator)
                .getCssValue("font-size").replace("px", ""));
    }

    public static float getFontSizeOfElement(WebElement element, String locator) {
        return Float.parseFloat(findElementInElement(element, locator)
                .getCssValue("font-size").replace("px", ""));
    }

    public static String getFontWeightOfElement(String locator) {
        return findElement(locator).getCssValue("font-weight");
    }

    public static String getFontWeightOfElement(WebElement element, String locator) {
        String cssValue = findElementInElement(element, locator).getCssValue("font-weight");
        if (cssValue.equals("bold") || cssValue.equals("700")) {
            return "bold";
        }
        return cssValue;
    }


    public static String getTextDecorationOfElement(String locator) {
        return findElement(locator).getCssValue("text-decoration");
    }

    public static String getTextDecorationOfElement(WebElement element, String locator) {
        return findElementInElement(element, locator).getCssValue("text-decoration").split(" ")[0];
    }


    public static void clickOnElementByJavaScript(String locator) {
        WebElement element = findElement(locator);
        clickOnElementByJavaScript(element);
    }

    public static void clickOnElementByJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static int getRandomInt(int max) {
        return new Random().nextInt(max);
    }


    public static ExpectedCondition<String> thereIsWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

}
