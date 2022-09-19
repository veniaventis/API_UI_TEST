package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import framework.logger.LoggerUtils;
import org.openqa.selenium.By;

public class Input extends BaseElement {

    public Input(By locator, String name) {
        super(locator, name);
    }

    public void sendKeys(String text) {
        LoggerUtils.info(getName() + " sending: " + text);
        getElementAsWebElement().sendKeys(text);
    }
}