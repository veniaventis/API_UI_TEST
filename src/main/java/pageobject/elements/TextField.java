package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import framework.logger.LoggerUtils;
import org.openqa.selenium.By;

public class TextField extends BaseElement {

    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void sendText(String text) {
        LoggerUtils.info(getName() + " sending: " + text);
        getElementAsWebElement().sendKeys(text);
    }

    public String getValue() {
        LoggerUtils.info(getName() + ": get value");
        return getElementAsWebElement().getAttribute("value");
    }

}