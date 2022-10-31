package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthenticationPage extends Form {
    ITextBox txbLogin = getElementFactory().getTextBox(By.id("index_email"), "Login field");
    ITextBox txbPassword = getElementFactory().getTextBox(By.id("index_pass"), "Password field");
    IButton btnLogin = getElementFactory().getButton(By.id("index_login_button"), "Login button");

    public AuthenticationPage() {
        super(By.id("index_login_form"), "Authorization form");
    }

    public void signIn(String login, String password) {
        txbLogin.clearAndType(login);
        txbPassword.clearAndTypeSecret(password);
        btnLogin.click();
    }
}
