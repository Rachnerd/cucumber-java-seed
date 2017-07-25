package nl.energiedirect.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import nl.energiedirect.test.runner.node_config.NodeConfigLoader;
import nl.energiedirect.test.runner.node_config.dtos.CapabilitiesDTO;
import nl.energiedirect.test.runner.node_config.dtos.NodeConfigDTO;
import nl.energiedirect.test.utils.driver.DriverFactory;
import nl.energiedirect.test.utils.driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;

@CucumberOptions(
        plugin = "json:target/cucumber.json",
        features = "src/test/resources/features/",
        glue = "nl.energiedirect.test.implementation.step_definitions",
        tags = {"~@Ignore"}
)
@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class RunCukesTest extends AbstractTestNGCucumberTests {
    private final Logger log = LogManager.getLogger(getClass());

    @BeforeTest
    @Parameters({"nodeConfig"})
    public void initDriver(String nodeConfigFile) throws MalformedURLException {
        String nodeConfigPath = getNodeConfigPath(nodeConfigFile);
        NodeConfigDTO config = NodeConfigLoader.load(nodeConfigPath);

        AppiumDriver driver = DriverFactory.createInstance(config);
        LocalDriverManager.setDriver(driver);

        CapabilitiesDTO current = config.getCapabilities()[0];
        ThreadContext.put("threadId", current.getDeviceName());
        log.info("Instantiated Appium driver: " + current);
    }

    @AfterTest
    public void quitDriver() {
        LocalDriverManager.getDriver().quit();
    }

    private String getNodeConfigPath(String nodeConfigFile) {
        return File.separator + nodeConfigFile;
    }
}
