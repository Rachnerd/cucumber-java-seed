package nl.seed.cucumber.utils.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory that instantiates a driver.
 */
class BrowserDriverLocator {
    private static final Logger log = LogManager.getLogger(BrowserDriverLocator.class);

    static String getDriverPropertyName(DriverType type) {
        switch (type) {
            case HEADLESS:
            case CHROME:
                return "webdriver.chrome.driver";
            case FIREFOX:
                return "webdriver.gecko.driver";
        }
        return "";
    }

    static String getDriverPropertyValue(DriverType type) {
        switch (type) {
            case HEADLESS:
            case CHROME:
                return createDriverLocation("googlechrome", "chromedriver");
            case FIREFOX:
                return createDriverLocation("marionette", "geckodriver");
        }
        return "";
    }

    private static String createDriverLocation(String folder, String driver) {
        return "driver/" + getOS() + "/" + folder + "/" + getOSBit() + "bit/" + driver;
    }

    private static String getOSBit() {
        if (!getOS().equals("windows")) {
            String arch = System.getProperty("os.arch");
            return arch.substring(arch.length() - 2, arch.length());
        }
        // https://stackoverflow.com/questions/4748673/how-can-i-check-the-bitness-of-my-os-using-java-j2se-not-os-arch
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

        return arch != null && arch.endsWith("64")
                || wow64Arch != null && wow64Arch.endsWith("64")
                ? "64" : "32";
    }

    private static String getOS() {
        return System.getProperty("os.name").toLowerCase();
    }

}
