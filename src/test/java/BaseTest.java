import framework.utils.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import framework.utils.ConfigManager;
import framework.utils.DriverUtils;


public class BaseTest {


    @BeforeMethod
    public void setUp() {
        FileUtils.createDownloadDirectory();
        DriverUtils.initialize(ConfigManager.getImplicitlyWait());

    }

    @AfterMethod
    public void tearDown() {
        FileUtils.cleanDownloadDirectory();
        DriverUtils.quit();
    }

}