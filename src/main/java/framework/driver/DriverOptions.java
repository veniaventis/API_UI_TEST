package framework.driver;

import framework.utils.ConfigManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Hashtable;
import java.util.Map;

class DriverOptions {

    private DriverOptions() {}

    static ChromeOptions getChromeOptions() {
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("download.default_directory", ConfigManager.getDownloadPath());

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        return options;
    }

    static FirefoxOptions getFireFoxOptions(){

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dowload.default_directory", ConfigManager.getDownloadPath());

        return options;
    }



}
