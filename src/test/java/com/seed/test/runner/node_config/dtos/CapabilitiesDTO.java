package com.seed.test.runner.node_config.dtos;

public class CapabilitiesDTO {
    private String browserName;
    private String version;
    private String platform;
    private String deviceName;
    private String avd;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAvd() {
        return avd;
    }

    public void setAvd(String avd) {
        this.avd = avd;
    }

    @Override
    public String toString() {
        return "CapabilitiesDTO{" +
                "browserName='" + browserName + '\'' +
                ", version='" + version + '\'' +
                ", platform='" + platform + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", avd='" + avd + '\'' +
                '}';
    }

}
