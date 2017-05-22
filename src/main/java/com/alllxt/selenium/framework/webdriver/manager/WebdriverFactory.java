package com.alllxt.selenium.framework.webdriver.manager;

import com.alllxt.selenium.framework.configuration.Browser;
import com.alllxt.selenium.framework.configuration.ConfigUtils;
import com.alllxt.selenium.framework.logging.LogListener;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by atribushny on 09.05.2017.
 */
public class WebdriverFactory {
    public static final String DISABLE_INFOBARS_KEY = "disable-infobars";

    public static WebDriver createInstance() {
        WebDriver driver;
        EventFiringWebDriver driver1;
        Browser browser = ConfigUtils.getBrowser();
        if (ConfigUtils.getIsRemoteDriver()) {
            DesiredCapabilities dc;
            switch (browser) {
                case Firefox:
                    dc = DesiredCapabilities.firefox();
                    break;
                case Chrome:
                    dc = createChromeDC();
                    break;
                case Phantom:
                    dc = DesiredCapabilities.phantomjs();
                    break;
                case IE:
                    dc = DesiredCapabilities.internetExplorer();
                    break;
                default:
                    throw new IllegalStateException("Browser is not supported.");
            }
            URL url;
            try {
                url = new URL(ConfigUtils.getRemoteDriverHubUrl());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new Error("Wrong url for remote server hub.", e);
            }
            try {
                driver = new RemoteWebDriver(url, dc);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            } catch (NoSuchSessionException e) {
                System.out.println("ATTENTION: WEBDRIVER FAILED WITH: " + e.getClass().getName());
                System.out.println("Restarting driver");
                driver = new RemoteWebDriver(url, dc);
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            }
        } else {
            switch (browser) {
                case Firefox:
                    System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case Chrome:
                    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                    DesiredCapabilities dc = createChromeDC();
                    driver1 = new EventFiringWebDriver(new ChromeDriver(dc));
                    return driver1.register(new LogListener());
                case Phantom:
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "drivers/phantomjs.exe");
                    driver = new PhantomJSDriver(caps);
                    break;
                case IE:
                    System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
                    DesiredCapabilities capsIE = DesiredCapabilities.internetExplorer();
                    capsIE.setCapability("ignoreZoomSetting", true);
                    driver = new InternetExplorerDriver(capsIE);
                    break;
                default:
                    throw new IllegalStateException("Browser is not supported.");
            }
        }
        return driver;
    }

    private static DesiredCapabilities createChromeDC() {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(DISABLE_INFOBARS_KEY);
        dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return dc;
    }
}
