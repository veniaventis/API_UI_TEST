package pageobject.elements;

import framework.driver.pageobject.BaseElement;
import org.openqa.selenium.By;

public class Link extends BaseElement {

    public Link(By locator, String name) {
        super(locator, name);
    }

}