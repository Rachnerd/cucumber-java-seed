package nl.seed.cucumber.utils.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DriverUtils {
    private static final Logger log = LogManager.getLogger("DriverUtils");
    public static String SCREENSHOT_EXTENSION = "png";
    public static String SCREENSHOT_BASE_FOLDER = "screenshots";
    public static boolean isMocked;
    public static String host;

    public static void get(String url) {
        if (url.startsWith("http")) {
            LocalDriverManager.get().get(url);
            log.debug("Navigated to " + url);
        } else {
            LocalDriverManager.get().get(host + url);
            log.debug("Navigated to " + host + url);
        }
    }

    public static void takeScreenShot(String fileName) {
        WebDriver driver = LocalDriverManager.get();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(SCREENSHOT_BASE_FOLDER + "/" + fileName + "." + SCREENSHOT_EXTENSION);
        try {
            FileUtils.copyFile(scrFile, file);
        } catch (IOException e) {
            log.error("Failed to save screenshot\n");
            e.printStackTrace();
        }
    }

    public static boolean allInputsDisabled(List<WebElement> allInputs, List<WebElement> exceptions) {
        boolean allDisabled = allInputs
                .stream()
                .filter(input -> exceptions
                        .stream()
                        .noneMatch(exception -> input == exception)
                )
                .noneMatch(WebElement::isEnabled);
        boolean exceptionsEnabled = exceptions
                .stream()
                .allMatch(WebElement::isEnabled);
        return allDisabled && exceptionsEnabled;
    }

    public static WebElement getInputByFormControlName(List<WebElement> inputs, String formControlName) {
        Optional<WebElement> optionalInput = inputs
                .stream()
                .filter(element -> element.getAttribute("formcontrolname").equals(formControlName))
                .findFirst();

        if (optionalInput.isPresent()) {
            return optionalInput.get();
        } else {
            throw new RuntimeException("Unrecognized form input: " + formControlName);
        }
    }

    /**
     * Headless can't deal with normal clicks.
     */
    public static void click(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) LocalDriverManager.get();
        jse.executeScript("arguments[0].scrollIntoView()", element);
        element.click();
    }


    public static void waitUntilVisible(WebElement element, Integer timeOutInSeconds) {
        WebDriver driver = LocalDriverManager.get();
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
