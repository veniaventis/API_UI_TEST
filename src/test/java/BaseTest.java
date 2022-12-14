import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utlis.ConfigUtils;

public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(ConfigUtils.getSettingsData("url"));
    }

    @AfterMethod
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
