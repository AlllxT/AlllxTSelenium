package com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries;

import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.TreeSet;

import static com.alllxt.selenium.framework.utils.Tools.click;
import static com.alllxt.selenium.framework.utils.Tools.isCollectionsEqual;

/**
 * Created by atribushny on 10.05.2017.
 */
public class Countries extends AdminHomePage {

    private static final String COUNTRY_ROW = ".data-table tbody>tr";
    private static final String ZONE_CELL = "tbody td:nth-child(6)";
    private static final String COUNTRY_LINK = "//a[.='%s']";

    public boolean isCountriesSorted() {
        System.out.println("\n Verify that country is sorted");
        ArrayList<String> originalList = new ArrayList<>();
        TreeSet<String> sortedCountries = new TreeSet<>();
        for (WebElement country : driver.findElements(By.cssSelector(COUNTRY_ROW))) {
            String countryName = country.findElement(By.tagName("a")).getText().replace("Å", "A");
            originalList.add(countryName);
            sortedCountries.add(countryName);
            paintElement(country, "green");
        }
        return isCollectionsEqual(originalList, sortedCountries);
    }


    public ArrayList<String> getNonZeroZoneCountries() {

        ArrayList<String> noZeroZone = new ArrayList<>();
        for (WebElement country : driver.findElements(By.cssSelector(COUNTRY_ROW))) {
            paintElement(country,"red");
            if (!country.findElement(By.cssSelector(ZONE_CELL)).getText().equals("0")) {
                WebElement countryName = country.findElement(By.tagName("a"));
                noZeroZone.add(countryName.getText());
                paintElement(country,"green");
            }
        }
        return noZeroZone;
    }

    public boolean isNonZeroZoneSorted() {
        System.out.println("\n Verify that Non Zero country is sorted");
        for (int i = 0; i < getNonZeroZoneCountries().size(); i++) {
            click(String.format(COUNTRY_LINK, getNonZeroZoneCountries().get(i)));
            new Edit().checkSorting();
        }
        return true;
    }
}
