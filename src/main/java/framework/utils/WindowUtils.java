package framework.utils;

import framework.logger.LoggerUtils;

import java.util.ArrayList;

public final class WindowUtils {

    private WindowUtils() {}

    public static void switchToAnotherWindow() {
        LoggerUtils.info("switching to another window");
        String originalWindow = DriverUtils.getDriver().getWindowHandle();
        for (String windowHandle : DriverUtils.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                DriverUtils.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void switchToAnotherWindow(String currentWindowHandle) {
        LoggerUtils.info("switching to another window");
        for (String windowHandle : DriverUtils.getDriver().getWindowHandles()) {
            if (!currentWindowHandle.contentEquals(windowHandle)) {
                DriverUtils.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void switchToWindow(int windowNumber) {
        LoggerUtils.info("switching to window " + windowNumber);
        ArrayList<String> tabs = new ArrayList<> (DriverUtils.getDriver().getWindowHandles());
        DriverUtils.getDriver().switchTo().window(tabs.get(windowNumber - 1));
    }

    public static void closeCurrentWindow() {
        LoggerUtils.info("closing current window");
        String originalWindow = DriverUtils.getDriver().getWindowHandle();
        DriverUtils.close();
        switchToAnotherWindow(originalWindow);
    }

}