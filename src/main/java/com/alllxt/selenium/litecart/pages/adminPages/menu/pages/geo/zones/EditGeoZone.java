package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.geo.zones;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.utils.Tools.isCollectionsEqual;

/**
 * Created by atribushny on 11.05.2017.
 */
public class EditGeoZone extends GeoZones {
    private static final String EDIT_COUNTRY_FIELD = "tr td:nth-child(3)";
    private static final String CANCEL_BUTTON = "button[name='cancel']";

    public void checkSorting() {
        ArrayList<String> originalList = new ArrayList<>();
        TreeSet<String> sortedCountries = new TreeSet<>();
        List<WebElement> indexes = driver.findElements(By.cssSelector(EDIT_COUNTRY_FIELD));
        for (int i = 0; i < indexes.size(); i++) {
            String countryName = indexes.get(i).getText().trim();
            originalList.add(countryName);
            sortedCountries.add(countryName);
        }
        isCollectionsEqual(originalList, sortedCountries);
        findByCss(CANCEL_BUTTON).click();
    }

}
