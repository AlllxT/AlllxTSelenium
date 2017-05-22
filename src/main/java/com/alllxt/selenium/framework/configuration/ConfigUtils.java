package com.alllxt.selenium.framework.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by atribushny on 24.04.2017.
 */
public class ConfigUtils {

    private static final String PATH_TO_CONFIG_PROPERTY_KEY = "config/config.properties";
    private static final String BROWSER_KEY = "browser";
    private static final String NEW_PRODUCT_IMAGE_FILE_KEY = "new.product.image.path";
    private static final String IS_REMOTE_DRIVER_KEY = "is.remote.driver";
    private static final String REMOTE_DRIVER_HUB_URL_KEY = "remote.driver.hub";

    private static Browser browser;
    private static String newProductImageFilePath = "";
    private static boolean isRemoteDriver = false;
    private static String remoteDriverHubUrl = "";

    private static Properties propertiesFromFile;


    static {
        try {
            loadConfig();
            setBrowser();
            setNewProductImageFilePath();
            setIsRemoteDriver();
            setRemoteDriverHubUrl();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Config initialization failed");
        }
    }

    private static void loadConfig() {
        propertiesFromFile = new Properties();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(PATH_TO_CONFIG_PROPERTY_KEY);
            propertiesFromFile.load(inputStream);
            inputStream.close();
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setBrowser() {
        String settingBrowser = getProperty(BROWSER_KEY);
        switch (settingBrowser) {
            case ("IE"):
                browser = Browser.IE;
                break;
            case ("Chrome"):
                browser = Browser.Chrome;
                break;
            case ("Firefox"):
                browser = Browser.Firefox;
                break;
            case ("Phantom"):
                browser = Browser.Phantom;
                break;
            default:
                browser = Browser.Unknown;
                System.out.println("UNKNOWN BROWSER");

        }
    }

    public static Browser getBrowser() {
        return browser;
    }


    private static String getProperty(String propertyName) {
        if (System.getProperty(propertyName) != null) {
            return System.getProperty(propertyName);
        } else {
            return propertiesFromFile.getProperty(propertyName);
        }
    }

    public static String getNewProductImageFilePath() {
        return newProductImageFilePath;
    }

    private static void setNewProductImageFilePath() {
        newProductImageFilePath = getProperty(NEW_PRODUCT_IMAGE_FILE_KEY);
    }

    private static void setIsRemoteDriver() {
        isRemoteDriver = Boolean.parseBoolean(getProperty(IS_REMOTE_DRIVER_KEY));
    }

    public static boolean getIsRemoteDriver() {
        return isRemoteDriver;
    }

    private static void setRemoteDriverHubUrl() {
        remoteDriverHubUrl = getProperty(REMOTE_DRIVER_HUB_URL_KEY);
    }

    public static String getRemoteDriverHubUrl() {
        return remoteDriverHubUrl;
    }


}
