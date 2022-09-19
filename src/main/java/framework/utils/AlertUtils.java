package framework.utils;

import framework.logger.LoggerUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertUtils {
    public static Alert waitForAlert() {
        LoggerUtils.info("alert waiting");
        try {
            return WaitDriverUtils.getDriverWait().until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            LoggerUtils.error("alert hasn't been appeared");
            return null;
        }
    }

    public static boolean isThereAlertOnPage() {
        LoggerUtils.info("alert checking");
        try {
            WaitDriverUtils.getDriverWait().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void acceptOpenedAlert() {
        LoggerUtils.info("alert accepting");
        waitForAlert().accept();
    }


}
