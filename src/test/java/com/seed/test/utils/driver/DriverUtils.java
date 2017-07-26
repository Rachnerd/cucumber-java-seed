package com.seed.test.utils.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class DriverUtils {
    private static final Logger log = LogManager.getLogger("DriverUtils");
    public static String SCREENSHOT_EXTENSION = "png";
    public static String SCREENSHOT_BASE_FOLDER = "screenshots";

    public static void get(String url) {
        LocalDriverManager.getDriver().get(url);
    }

    public static void takeScreenShot(String fileName) {
        WebDriver driver = LocalDriverManager.getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(SCREENSHOT_BASE_FOLDER + "/" + fileName + "." + SCREENSHOT_EXTENSION);
        try {
            FileUtils.copyFile(scrFile, file);
        } catch (IOException e) {
            log.error("Failed to save screenshot\n");
            e.printStackTrace();
        }
    }
}
