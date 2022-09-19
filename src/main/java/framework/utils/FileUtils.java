package framework.utils;

import framework.logger.LoggerUtils;

import java.io.File;

public final class FileUtils{

    private FileUtils() {}

    public static boolean isFileDownloaded(String fileName) {
        LoggerUtils.info("download checking");
        return new File(ConfigManager.getDownloadPath() + fileName).exists();
    }

    public static void createDownloadDirectory() {
        LoggerUtils.info("download directory creation");
        File downloadDir = new File(ConfigManager.getDownloadPath());
        if (!downloadDir.exists()) {
            if (downloadDir.mkdir()) LoggerUtils.info(downloadDir.getName() + " has been created");
        }
    }

    public static void cleanDownloadDirectory() {
        LoggerUtils.info("download directory deleting");
        File[] downloadDirFiles = new File(ConfigManager.getDownloadPath()).listFiles();
        assert downloadDirFiles != null;
        for (File file: downloadDirFiles) {
            if (file.delete()) { LoggerUtils.info(file.getName() + "has been deleted"); }
            else { LoggerUtils.warn(file.getName() + "hasn't been deleted"); }
        }
    }

}
