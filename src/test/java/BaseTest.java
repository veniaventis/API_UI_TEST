import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected Browser browser;


    @BeforeMethod
    public void setUp() {
        String urlFromSettings = new JsonSettingsFile("settings.json").getValue("/url").toString();
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(urlFromSettings);
    }

    @AfterMethod
    public void tearDown() {
        browser.quit();
    }
}
