package com.alllxt.selenium.framework.webdriver.manager;

import com.alllxt.selenium.framework.configuration.Browser;
import com.alllxt.selenium.framework.configuration.ConfigUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by atribushny on 09.05.2017.
 */
public class WebdriverFactory {

    public static WebDriver createInstance() {
        WebDriver driver;
        Browser browser = ConfigUtils.getBrowser();
        switch (browser) {
            case Firefox:
                System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case Chrome:
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
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
        return driver;
    }

}
