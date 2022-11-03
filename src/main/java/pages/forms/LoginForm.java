package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {
    private final ITextBox txbLogin = getElementFactory().getTextBox(By.id("index_email"), "Login field");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//button[contains(@class,'VkIdForm__signInButton')]"), "SignIn button");

    public LoginForm() {
        super(By.xpath("//form[contains(@class,'VkIdForm__form')]"),"Login Form");
    }

    public void signIn(String login) {
        txbLogin.clearAndType(login);
        btnSignIn.click();
    }
}
