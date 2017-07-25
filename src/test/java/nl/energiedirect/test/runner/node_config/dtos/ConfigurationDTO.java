package nl.energiedirect.test.runner.node_config.dtos;

public class ConfigurationDTO {
    private String hubPort;
    private String hubHost;

    public String getHubPort() {
        return hubPort;
    }

    public void setHubPort(String hubPort) {
        this.hubPort = hubPort;
    }

    public String getHubHost() {
        return hubHost;
    }

    public void setHubHost(String hubHost) {
        this.hubHost = hubHost;
    }

    @Override
    public String toString() {
        return "ConfigurationDTO{" +
                "hubPort='" + hubPort + '\'' +
                ", hubHost='" + hubHost + '\'' +
                '}';
    }
}
