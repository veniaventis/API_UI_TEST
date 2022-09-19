package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import framework.logger.LoggerUtils;
import org.openqa.selenium.By;

public class TextElement extends BaseElement {

    public TextElement(By locator, String name) {
        super(locator, name);
    }

    public String getText() {
        LoggerUtils.info("get text from " + getName());
        return getElementAsWebElement().getText();
    }

}
