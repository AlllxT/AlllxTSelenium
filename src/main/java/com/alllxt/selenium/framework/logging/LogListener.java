package com.alllxt.selenium.framework.logging;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by atribushny on 22.05.2017.
 */
public class LogListener extends AbstractWebDriverEventListener {

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("The page is opened " + url);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("The element was found " + by);
    }


    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("Exception: " + "\n" + throwable);
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenShot = new File("screenshotsAlllxT/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tempFile, screenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(screenShot);
    }
}
