package framework.driver;

import framework.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import framework.logger.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class BrowserFactory {

    private BrowserFactory() {}

    static WebDriver getDriver() {

        String browserName = ConfigManager.getBrowserName().toUpperCase();

        LoggerUtils.warn(browserName + " browser running");

        switch (browserName) {
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(DriverOptions.getChromeOptions());
            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(DriverOptions.getFireFoxOptions());
            }
            default -> throw new RuntimeException("Incorrect BrowserName in Configuration File");
        }

    }

}