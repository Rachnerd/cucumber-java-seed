package nl.seed.cucumber.utils.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;


/**
 * Factory that instantiates a driver.
 */
public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createInstance(DriverType type) {
        WebDriver driver = null;
        String logMessage = "";
        switch (type) {
            case HEADLESS: {
                ChromeDriverManager.getInstance().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                try {
                    logMessage = "ChromeDriver (headless) started:" + chromeOptions.toJson().toString();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
            case CHROME: {
                ChromeDriverManager.getInstance().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--window-size=1280,800");
                driver = new ChromeDriver(chromeOptions);
                try {
                    logMessage = "ChromeDriver started: " + chromeOptions.toJson().toString();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
            case FIREFOX: {
                FirefoxDriverManager.getInstance().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--window-size=1280,800");
                driver = new FirefoxDriver(firefoxOptions);
                try {
                    logMessage = "FirefoxDriver started: " + firefoxOptions.toJson().toString();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            break;
        }

        log.info(logMessage);
        return driver;
    }

}
