package com.seed.test.utils.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * This class stores the driver instantiated by the DriverFactory.
 * TestNG can execute multiple test cases at the same time. Assigning multiple drivers to the same variable
 * will not work because each will override the previous. Each parallel test is ran in a separate "Thread".
 * Storing each driver in a ThreadLocal variable makes sure every parallel test interacts with the its own driver.
 */
public class LocalDriverManager extends AbstractTestNGSpringContextTests {
    private static ThreadLocal<WebDriver> mobileDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return mobileDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        mobileDriver.set(driver);
    }
}
