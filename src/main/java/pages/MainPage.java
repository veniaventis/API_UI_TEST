package pages;

import aquality.selenium.forms.Form;
import pages.forms.LoginForm;
import org.openqa.selenium.By;

public class MainPage extends Form {

    public MainPage() {
        super(By.id("index_login"),"MainPage");
    }

    public LoginForm getLoginForm() {
        return new LoginForm();
    }
}
