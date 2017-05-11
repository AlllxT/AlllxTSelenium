package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.utils.Tools.isCollectionsEqual;

/**
 * Created by atribushny on 11.05.2017.
 */
public class Edit extends Countries {
    private static final String ZONE_ROWS = "input[name*='[id]']";
    private static final String EDIT_COUNTRY_FIELD = "input[name='zones[%s][name]']";
    private static final String CANCEL_BUTTON = "button[name='cancel']";

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
        findByCss(CANCEL_BUTTON).click();
    }


}
