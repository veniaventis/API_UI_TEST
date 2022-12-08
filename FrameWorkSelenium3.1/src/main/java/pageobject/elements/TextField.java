package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import org.openqa.selenium.By;

public class TextField extends BaseElement {

    public TextField(By locator, String name) {
        super(locator, name);
    }

    public void sendText(String text) {
        log.info(getName() + " sending: " + text);
        getElementAsWebElement().sendKeys(text);
    }

    public String getValue() {
        log.info(getName() + ": get value");
        return getElementAsWebElement().getAttribute("value");
    }

}