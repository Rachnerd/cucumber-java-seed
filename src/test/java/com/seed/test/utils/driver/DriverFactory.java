package com.seed.test.utils.driver;

import com.seed.test.runner.node_config.dtos.CapabilitiesDTO;
import com.seed.test.runner.node_config.dtos.ConfigurationDTO;
import com.seed.test.runner.node_config.dtos.NodeConfigDTO;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Factory that instantiates a driver.
 */
public class DriverFactory {

    public static WebDriver createInstance(NodeConfigDTO config) throws MalformedURLException {
        ConfigurationDTO nodeConfig = config.getConfiguration();
        CapabilitiesDTO nodeCapabilities = config.getCapabilities()[0];

        URL hubURL = new URL("http://" + nodeConfig.getHubHost() + ':' + nodeConfig.getHubPort() + "/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, nodeCapabilities.getBrowserName());
        WebDriver driver = new RemoteWebDriver(hubURL, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
