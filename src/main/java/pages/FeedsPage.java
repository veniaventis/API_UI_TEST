package pages;

import aquality.selenium.forms.Form;
import pages.forms.NavigationMenuForm;
import org.openqa.selenium.By;

public class FeedsPage extends Form {

    public FeedsPage() {
        super(By.xpath("//div[contains(@class,'stories_feed_wrap')]"), "Feeds Page");
    }

    public NavigationMenuForm getNavigationMenuForm() {
        return new NavigationMenuForm();
    }
}
