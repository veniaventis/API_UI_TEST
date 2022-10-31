package forms;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavigationMenuForm extends Form {
    ITextBox txbMyPage = getElementFactory().getTextBox(By.id("l_pr"), "My page button");

    public NavigationMenuForm() {
        super(By.id("side_bar_inner"), "Navigation menu");
    }

    public void clickMyPageBtn() {
        txbMyPage.state().waitForClickable();
        txbMyPage.click();
    }
}
