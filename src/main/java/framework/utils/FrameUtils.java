package framework.utils;

import framework.logger.LoggerUtils;
import org.openqa.selenium.By;

public final class FrameUtils{

    public static void switchToIframe(By locator) {
        LoggerUtils.info("switch to iframe");
        DriverUtils.getDriver().switchTo().frame(DriverUtils.getDriver().findElement(locator));
    }

    public static void backToDefaultContent() {
        LoggerUtils.info("switch back from iframe");
        DriverUtils.getDriver().switchTo().defaultContent();
    }

}
