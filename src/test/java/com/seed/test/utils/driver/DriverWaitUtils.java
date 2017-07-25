package com.seed.test.utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWaitUtils {

    public static void waitUntilVisible(MobileElement element, Integer timeOutInSeconds) {

        AppiumDriver driver = LocalDriverManager.getDriver();

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
