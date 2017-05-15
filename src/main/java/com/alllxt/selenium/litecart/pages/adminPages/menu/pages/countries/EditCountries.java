package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.utils.Tools.*;
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
        for (WebElement externalLink : externalLinkList) {
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            String currentTitle = driver.getTitle();
            externalLink.click();
            String newWindow = wait.until(thereIsWindowOtherThan(allWindows));
            driver.switchTo().window(newWindow);
            String newWindowTitle = driver.getTitle();
            System.out.println(currentTitle);
            System.out.println(newWindowTitle);
            assertNotEquals(currentTitle, newWindowTitle);
            driver.close();
            driver.switchTo().window(currentWindow);
        }

    }


}
