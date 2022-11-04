package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NavigationMenuForm extends Form {
    private final IButton btnMyPage = getElementFactory().getButton(By.id("l_pr"), "My page button");

    public NavigationMenuForm() {
        super(By.id("side_bar_inner"), "Navigation menu");
    }

    public void clickMyPageBtn() {
        btnMyPage.state().waitForClickable();
        btnMyPage.click();
    }
}
