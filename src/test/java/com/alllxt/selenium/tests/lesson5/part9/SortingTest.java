package com.alllxt.selenium.tests.lesson5.part9;

import com.alllxt.selenium.framework.bases.BaseTest;
import com.alllxt.selenium.framework.models.User;
import com.alllxt.selenium.litecart.pages.adminPages.AdminHomePage;
import com.alllxt.selenium.litecart.pages.adminPages.AdminLoginPage;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.countries.Countries;
import com.alllxt.selenium.litecart.pages.adminPages.menu.pages.geo.zones.GeoZones;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.alllxt.selenium.framework.utils.Tools.titleIsPresent;
import static org.testng.Assert.assertTrue;

/**
 * Created by atribushny on 10.05.2017.
 */
public class SortingTest extends BaseTest {

    private User user = new User();
    private AdminLoginPage adminLoginPage;
    private AdminHomePage adminHomePage;
    private Countries adminCountries;
    private GeoZones geoZones;


    @BeforeMethod
    public void setUpTestClass() {
        adminLoginPage = new AdminLoginPage();
        adminHomePage = new AdminHomePage();
        adminCountries = new Countries();
        geoZones = new GeoZones();
    }

    @Test(enabled = true)
    public void countrySorting() {
        adminLoginPage.openAndLogin(user);
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        assertTrue(titleIsPresent(wait, "Countries | My Store"), "Title is not correct ");
        //a) Verify country is alphabet sorted
        assertTrue(adminCountries.isCountriesSorted());
        //b) Verify non zero zone countries is alphabet sorted
        assertTrue(adminCountries.isNonZeroZoneSorted());
        adminHomePage.logout();

    }

    @Test(enabled = true)
    public void zoneSorting() {
        adminLoginPage.openAndLogin(user);
        assertTrue(titleIsPresent(wait, "My Store"), "Title is not correct ");
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        assertTrue(titleIsPresent(wait, "Geo Zones | My Store"), "Title is not correct ");
        //Open every country in list and verify zones are alphabet sorted
        assertTrue(geoZones.isGeoZoneCountrySorted());
        adminHomePage.logout();
    }

}
