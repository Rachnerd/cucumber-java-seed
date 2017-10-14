package nl.seed.cucumber.utils.hooks;

import nl.seed.cucumber.utils.driver.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class DriverHooks {
    private final Logger log = LogManager.getLogger(getClass());
    /**
     * Takes care of starting the correct driver before each test.
     *
     * @param browserNameParameter - Name of the browser that will be transformed to DriverType
     */
    @BeforeTest(alwaysRun = true)
    @Parameters({"browserName"})
    public void initDriver(@Optional() String browserNameParameter) {
        String browserName = ParameterUtils.getParameter(browserNameParameter, "browserName", "headless");

        /*
         * Set log prefix
         */
        ThreadContext.put("threadId", browserName);

        DriverType type = DriverIdentifier.getDriverType(browserName);
        WebDriver driver = DriverFactory.createInstance(type);
        driver
                .manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);

        LocalDriverManager.set(driver);
    }


    /**
     * There are two ways to set the host
     * - Pass System parameter by adding -Dhost=https://... to the run command
     * - Pass TestNG parameter
     * If both are declared, the System property gets prioritised.
     *
     * @param hostParameter - Base url of the application.
     */
    @BeforeTest(alwaysRun = true)
    @Parameters({"host"})
    public void setHost(@Optional String hostParameter) {
        try {
            DriverUtils.host = ParameterUtils.getParameter(hostParameter, "host");
        }catch(Exception e) {
            throw new RuntimeException("No host specified. Provide a host parameter in TestNG, or pass -Dhost=https://...");
        }
    }

    /**
     * Closes the driver after the test case (after all scenario's)
     */
    @AfterTest(alwaysRun = true)
    public void quitDriver() {
        LocalDriverManager.get().quit();
    }
}
