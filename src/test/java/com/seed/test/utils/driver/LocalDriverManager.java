package com.seed.test.utils.driver;
import io.appium.java_client.AppiumDriver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * This class stores the driver instantiated by the DriverFactory.
 * TestNG can execute multiple test cases at the same time. Assigning multiple drivers to the same variable
 * will not work because each will override the previous. Each parallel test is ran in a separate "Thread".
 * Storing each driver in a ThreadLocal variable makes sure every parallel test interacts with the its own driver.
 */
public class LocalDriverManager extends AbstractTestNGSpringContextTests {
    private static ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return mobileDriver.get();
    }

    public static void setDriver(AppiumDriver driver) {
        mobileDriver.set(driver);
    }
}
