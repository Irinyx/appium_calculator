package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        AppiumDriverLocalService service = AppiumService.getService();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.getString("android.emulator_name"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        //capabilities.setCapability("avd", base.Config.getString("android.emulator_name"));
        capabilities.setCapability("app", new File(Config.getString("android.apk")).getAbsolutePath());
        capabilities.setCapability("appPackage", Config.getString("android.package"));
        capabilities.setCapability("automationName", "UiAutomator2");

        capabilities.setCapability("appActivity", Config.getString("android.activity"));
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
        AppiumService.stopService();
    }
}
