package com.seed.test.utils.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import com.seed.test.runner.node_config.dtos.CapabilitiesDTO;
import com.seed.test.runner.node_config.dtos.ConfigurationDTO;
import com.seed.test.runner.node_config.dtos.NodeConfigDTO;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static AppiumDriver createInstance(NodeConfigDTO config) throws MalformedURLException {
        ConfigurationDTO nodeConfig = config.getConfiguration();
        CapabilitiesDTO nodeCapabilities = config.getCapabilities()[0];

        URL hubURL = new URL("http://" + nodeConfig.getHubHost() + ':' + nodeConfig.getHubPort() + "/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        List<String> avdArgs = new ArrayList<>();

        if (System.getProperty("proxyUrl") != null) {
            avdArgs.add("-http-proxy " + System.getProperty("proxyUrl"));
        }
        avdArgs
                .stream()
                .reduce((avdArgsString, arg) -> avdArgsString + " " + arg)
                .ifPresent((avdArgsString) -> capabilities.setCapability("avdArgs", avdArgsString));

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, nodeCapabilities.getPlatform());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, nodeCapabilities.getVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, nodeCapabilities.getDeviceName());
        capabilities.setCapability("avd", nodeCapabilities.getAvd());
        capabilities.setCapability(MobileCapabilityType.APP, new File(ClassLoader.getSystemResource("TestApp-debug.apk")
                .getFile()).getAbsolutePath());
        AppiumDriver driver = new AndroidDriver(hubURL, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
