package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.utils.Tools.*;
import static com.alllxt.selenium.framework.utils.WebdriverUtils.waitAllWindowsToOpen;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by atribushny on 11.05.2017.
 */
public class EditCountries extends Countries {
    private static final String ZONE_ROWS = "input[name*='[id]']";
    private static final String EDIT_COUNTRY_FIELD = "input[name='zones[%s][name]']";
    private static final String CANCEL_BUTTON = "button[name='cancel']";
    private static final String EXTERNAL_LINK = "form a[target='_blank']";

    public void checkSorting() {
        ArrayList<String> originalList = new ArrayList<>();
        TreeSet<String> sortedCountries = new TreeSet<>();
        List<WebElement> indexes = driver.findElements(By.cssSelector(ZONE_ROWS));
        for (int i = 0; i < indexes.size(); i++) {
            String index = indexes.get(i).getAttribute("value");
            String countryName = driver.findElement(By.cssSelector(
                    String.format(EDIT_COUNTRY_FIELD, index))).getAttribute("value").trim();
            originalList.add(countryName);
            sortedCountries.add(countryName);
        }
        isCollectionsEqual(originalList, sortedCountries);
        findElement(CANCEL_BUTTON).click();
    }


    public void externalLinkVerify() {
        wait.until(visibilityOfAllElementsLocatedBy(getByFromString(EXTERNAL_LINK)));
        List<WebElement> externalLinkList = findElements(EXTERNAL_LINK);
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        String currentUrl = driver.getCurrentUrl();
        String currentTitle = driver.getTitle();
        for (WebElement externalLink : externalLinkList) {
            wait.until(ExpectedConditions.elementToBeClickable(externalLink));
            externalLink.click();
            String newWindow = wait.until(thereIsWindowOtherThan(allWindows));
            driver.switchTo().window(newWindow);
            waitAllWindowsToOpen(allWindows.size());
            wait.until(ExpectedConditions.not(urlToBe("about:blank")));
            String newWindowTitle = driver.getTitle();
            String newWindowURL = driver.getCurrentUrl();
            System.out.println("Back to Country Editor by URL: " + currentUrl);
            System.out.println("New window has URL: " + newWindowURL);
            assertNotEquals(currentTitle, newWindowTitle);
            assertNotEquals(currentUrl, newWindowTitle);
            driver.close();
            driver.switchTo().window(currentWindow);
        }


    }
}
