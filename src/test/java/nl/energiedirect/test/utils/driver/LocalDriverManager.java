package nl.energiedirect.test.utils.driver;

import io.appium.java_client.AppiumDriver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

public class LocalDriverManager extends AbstractTestNGSpringContextTests {
    private static ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return mobileDriver.get();
    }

    public static void setDriver(AppiumDriver driver) {
        mobileDriver.set(driver);
    }
}
