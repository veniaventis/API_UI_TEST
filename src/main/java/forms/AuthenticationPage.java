package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthenticationPage extends Form {
    private final ITextBox txbLogin = getElementFactory().getTextBox(By.id("index_email"), "Login field");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'vkc__Password__Wrapper')]//child::input"), "Password field");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//button[contains(@class,'VkIdForm__signInButton')]"), "SignIn button");
    private final IButton btnContinue = getElementFactory().getButton(By.xpath("//button[contains(@class,'vkuiButton--clr-accent ')]"), "Login button");

    public AuthenticationPage() {
        super(By.id("index_login_form"), "Authorization form");
    }

    public void signIn(String login, String password) {
        txbLogin.clearAndType(login);
        btnSignIn.click();
        txbPassword.clearAndTypeSecret(password);
        btnContinue.click();
    }
}
