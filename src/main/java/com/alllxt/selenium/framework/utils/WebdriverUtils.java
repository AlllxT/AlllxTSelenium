package com.alllxt.selenium.framework.utils;

import com.alllxt.selenium.framework.webdriver.manager.LocalDriverManager;
import com.alllxt.selenium.framework.webdriver.manager.WebdriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.alllxt.selenium.framework.utils.Tools.findElement;
import static com.alllxt.selenium.framework.utils.Tools.getByFromString;

/**
 * Created by atribushny on 09.05.2017.
 */
public class WebdriverUtils {

    public static WebDriver createNewDriver() {
        WebDriver webDriver = WebdriverFactory.createInstance();
        LocalDriverManager.setWebDriver(webDriver);
        return webDriver;
    }

    public static void instantiateDriver() {
        WebDriver driver = LocalDriverManager.getDriver();
        try {
            if (driver == null) {
                System.out.println("Webdriver is not set. Creating new driver");
                WebdriverUtils.createNewDriver();
                setMaximiseBrowserWindow();
                return;
            }
            driver.getWindowHandles();
            driver.getTitle();
        } catch (Exception exception) {
            System.out.println("Browser is not detected. Error message: \""
                    + exception.getMessage() + "\". Restarting browser");
            WebdriverUtils.createNewDriver();
            setMaximiseBrowserWindow();
        }
    }

    public static void quitDriver() {
        if (LocalDriverManager.getDriver() != null) {
            try {
                LocalDriverManager.getDriver().quit();
                LocalDriverManager.setWebDriver(null);
            } catch (Exception exception) {
                String errorMSG = exception.getMessage();
                if (errorMSG == null) {
                    errorMSG = "The error message is empty.";
                }
            } finally {
                LocalDriverManager.setWebDriver(null);
            }
        }
    }

    public static void setMaximiseBrowserWindow() {
        WebDriver driver = LocalDriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(LocalDriverManager.getDriver(), 30);

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) LocalDriverManager.getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) LocalDriverManager.getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }


    public static void waitAllWindowsToOpen(int numberOfInitialOpenedWindows) {
        final WebDriver driver = LocalDriverManager.getDriver();
        try {
            new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return (driver.getWindowHandles().size() == (numberOfInitialOpenedWindows + 1));
                }
            });
        } catch (TimeoutException ignored) {

        }
    }

    public static WebElement findElementInFrame(String locator) {
        switchToDefaultContent();
        By by = getByFromString(locator);
        WebDriver driver = LocalDriverManager.getDriver();
        String selectScript;
        if (by instanceof By.ByXPath) {
            selectScript = String.format("document.evaluate(\"%s\", this.contentDocument, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;", locator);
        } else {
            selectScript = String.format("$(this).contents().find(\"%s\")[0];", locator);
        }
        String findFrameScript = "var callback = arguments[arguments.length - 1];\n" +
                "$('iframe, frame').each(function(){\n" +
                " var el = " + selectScript + "\n" +
                " if (el){\n" +
                "  callback(this);\n" +
                " };\n" +
                "});";
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        WebElement iframe;
        try {
            iframe = (WebElement) js.executeAsyncScript(findFrameScript);
        } catch (WebDriverException ex) {
            throw new TimeoutException("Couldn\'t find element with locator " + locator + " in any frame.");
        }
        if (iframe == null) {
            throw new NoSuchElementException("Couldn\'t find element with locator " + locator + " in any frame.");
        }
        driver.switchTo().frame(iframe);
        return findElement(locator);
    }

    private static void switchToDefaultContent() {
        LocalDriverManager.getDriver().switchTo().defaultContent();
    }


}
