package com.alllxt.selenium.framework.logging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Created by atribushny on 22.05.2017.
 */
public class LogListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigating to " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("The page is opened " + url);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Searching the element " + element);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("The element was found " + element + " by " + by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("Exception: " + "\n" + throwable);
    }
}
