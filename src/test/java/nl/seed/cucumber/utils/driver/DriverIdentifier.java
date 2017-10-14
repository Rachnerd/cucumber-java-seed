package nl.seed.cucumber.utils.driver;

public class DriverIdentifier {

    public static DriverType getDriverType(String browserName) {
        switch (browserName.toLowerCase()) {
            case "headless":
                return DriverType.HEADLESS;
            case "chrome":
                return DriverType.CHROME;
            case "firefox":
                return DriverType.FIREFOX;
            default:
                throw new RuntimeException(browserName + " not recognized as DriverType");
        }
    }
}
