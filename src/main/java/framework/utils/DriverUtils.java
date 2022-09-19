package framework.utils;

import framework.driver.SingletonDriver;
import framework.logger.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public final class DriverUtils{

    private static WebDriver driver;

    private DriverUtils() {}

    public static void initialize(int implicitlyWaitSeconds) {
        LoggerUtils.warn("initialization driver");
        driver = SingletonDriver.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitSeconds));
        driver.manage().window().maximize();
        WaitDriverUtils.updateDriverWait();
    }

    public static void loadURL(String URL) {
        LoggerUtils.info("URL loading");
        SingletonDriver.getDriver().get(URL);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quit() {
        LoggerUtils.warn("driver quiting");
        driver.quit();
        SingletonDriver.resetDriver();
    }

    public static void close() {
        if (driver.getWindowHandles().size() > 1) {
            LoggerUtils.info("window closing");
            driver.close();
        } else {
            driver.quit();
        }
    }

    public static void setImplicitlyWait(int seconds) {
        LoggerUtils.warn("implicit wait setting to " + seconds + " seconds");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

}