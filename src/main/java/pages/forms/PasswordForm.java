package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordForm extends Form {
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'vkc__Password__Wrapper')]//child::input"), "Password field");
    private final IButton btnContinue = getElementFactory().getButton(By.xpath("//button[contains(@class,'vkuiButton--clr-accent ')]"), "Login button");


    public PasswordForm() {
        super(By.xpath("//form[contains(@class,'vkc__EnterPasswordNoUserInfo__content')]"), "Password Form");
    }

    public void signIn(String password) {
        txbPassword.clearAndType(password);
        btnContinue.click();
    }

}
