package nl.energiedirect.test.runner.node_config.dtos;

import java.util.Arrays;

public class NodeConfigDTO {
    private CapabilitiesDTO[] capabilities;

    private ConfigurationDTO configuration;

    public CapabilitiesDTO[] getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(CapabilitiesDTO[] capabilities) {
        this.capabilities = capabilities;
    }

    public ConfigurationDTO getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ConfigurationDTO configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "NodeConfigDTO{" +
                "capabilities=" + Arrays.toString(capabilities) +
                ", configuration=" + configuration +
                '}';
    }
}
