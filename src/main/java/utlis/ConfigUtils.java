package utlis;

import aquality.selenium.core.utilities.JsonSettingsFile;

public class ConfigUtils {
    public static String getTestData(String key) {
        return new JsonSettingsFile("TestData.json").getValue(String.format("/%s", key)).toString();
    }

    public static String getSettingsData(String key) {
        return new JsonSettingsFile("settings.json").getValue(String.format("/%s", key)).toString();
    }

    public static String getConfidentialData(String key) {
        return new JsonSettingsFile("ConfidentialData.json").getValue(String.format("/%s", key)).toString();
    }
}
