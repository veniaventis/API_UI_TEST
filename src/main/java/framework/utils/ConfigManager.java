package framework.utils;

import framework.logger.LoggerUtils;
import models.User;

import java.io.*;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        LoggerUtils.info("config manager initialization");
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConfigManager() {}

    public static String getURL() {
        LoggerUtils.info("URL getting");
        return properties.getProperty("URL");
    }

    public static String getBrowserName() {
        LoggerUtils.info("BROWSER_NAME getting");
        return properties.getProperty("BROWSER_NAME");
    }
    public static int getImplicitlyWait() {
        LoggerUtils.info("IMPLICIT_WAIT getting");
        return Integer.parseInt(properties.getProperty("IMPLICIT_WAIT"));
    }

    public static int getExplicitlyWait() {
        LoggerUtils.info("EXPLICIT_WAIT getting");
        return Integer.parseInt(properties.getProperty("EXPLICIT_WAIT"));
    }

    public static int getFluentWaitFrequency() {
        LoggerUtils.info("FLUENT_WAIT_MILLIS_FREQUENCY getting");
        return Integer.parseInt(properties.getProperty("FLUENT_WAIT_MILLIS_FREQUENCY"));
    }

    public static String getDownloadPath() {
        LoggerUtils.info("DOWNLOAD_PATH getting");
        return System.getProperty("user.dir") + properties.getProperty("DOWNLOAD_PATH");
    }

    public static String getDownloadFileName() {
        LoggerUtils.info("DOWNLOAD_FILE_NAME getting");
        return properties.getProperty("DOWNLOAD_FILE_NAME");
    }

    public static int getAuthorAge() {
        LoggerUtils.info("AUTHOR_AGE getting");
        return Integer.parseInt(properties.getProperty("AUTHOR_AGE"));
    }

    public static User getUser(int userN) {
        LoggerUtils.info("get user " + userN + "from config");
        String[] userData = properties.getProperty("User" + userN).split(", ");
        return new User(
                userData[0],
                userData[1],
                userData[2],
                Integer.parseInt(userData[3]),
                Integer.parseInt(userData[4]),
                userData[5]
        );
    }

}