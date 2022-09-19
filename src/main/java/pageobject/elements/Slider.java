package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import framework.logger.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import framework.utils.DriverUtils;

public class Slider extends BaseElement {

    public Slider(By locator, String name) {
        super(locator, name);
    }

    public void clickAndHold() {
        LoggerUtils.info("Slider holding");
        Actions actions = new Actions(DriverUtils.getDriver());
        actions.clickAndHold(getElementAsWebElement()).perform();
    }

    public void release() {
        LoggerUtils.info("Slider release");
        Actions actions = new Actions(DriverUtils.getDriver());
        actions.release(getElementAsWebElement()).perform();
    }

}