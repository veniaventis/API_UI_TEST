package framework.driver.pageobject;

import framework.utils.*;
import org.openqa.selenium.JavascriptExecutor;
import pageobject.elements.UniqElement;
import framework.logger.LoggerUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BaseForm {

    private final UniqElement uniqElement;
    private final String name;


    protected BaseForm(By uniqElement, String name) {
        this.uniqElement = new UniqElement(uniqElement, getName() + ":uniqElement");
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isOpened() {
        LoggerUtils.info("page checking");
        return uniqElement.isExist();
    }

    public Alert waitForAlert() {
        LoggerUtils.info("alert waiting");
        try {
            return WaitDriverUtils.getDriverWait().until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            LoggerUtils.error("alert hasn't been appeared");
            return null;
        }
    }

    public boolean isThereAlertOnPage() {
        LoggerUtils.info("alert checking");
        try {
            WaitDriverUtils.getDriverWait().until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUrlContains(String text) {
        DriverUtils.setImplicitlyWait(0);
        boolean result = WaitDriverUtils.getDriverWait().until(ExpectedConditions.urlContains(text));
        DriverUtils.setImplicitlyWait(ConfigManager.getImplicitlyWait());
        return result;
    }
    public void scrollingDown(){
        JavascriptExecutor js = (JavascriptExecutor) DriverUtils.getDriver();
        js.executeScript("window.scrollBy(0,250)","");
    }

}