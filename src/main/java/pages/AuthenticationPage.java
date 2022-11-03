package pages;

import aquality.selenium.forms.Form;
import pages.forms.PasswordForm;
import org.openqa.selenium.By;

public class AuthenticationPage extends Form {
    public AuthenticationPage() {
        super(By.xpath("//div[contains(@class,'vkc__Password__Wrapper')]"), "Authorization Page");
    }

    public PasswordForm getPasswordForm() {
        return new PasswordForm();
    }
}
