package api.utlis;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigUtils {
    public static String getTestData(String key) {
        return new JsonSettingsFile("TestData.json").getValue(String.format("/%s", key)).toString();
    }

    public static String getSettingsDataString(String key){
        return new JsonSettingsFile("Settings.json").getValue(String.format("/%s",key)).toString();
    }
}
