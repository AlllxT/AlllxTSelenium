package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.geo.zones;

import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.alllxt.selenium.framework.utils.Tools.click;

/**
 * Created by atribushny on 11.05.2017.
 */
public class GeoZones extends AdminHomePage{

    private static final String COUNTRY_ROW = ".data-table tbody>tr";
    private static final String COUNTRY_LINK = "//a[.='%s']";

    private ArrayList<String> getGeoZoneCountries() {
        ArrayList<String> countries = new ArrayList<>();
        for (WebElement country : driver.findElements(By.cssSelector(COUNTRY_ROW))) {
                countries.add(country.findElement(By.tagName("a")).getText());
        }
        return countries;
    }

    public boolean isGeoZoneCountrySorted() {
        System.out.println("\n Verify that GeoZone has sorted countries");
        for (int i = 0; i < getGeoZoneCountries().size(); i++) {
            click(String.format(COUNTRY_LINK, getGeoZoneCountries().get(i)));
            new EditGeoZone().checkSorting();
        }
        return true;
    }
}
