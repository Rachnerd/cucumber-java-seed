package com.seed.test.runner.node_config;

import com.google.gson.Gson;
import com.seed.test.runner.node_config.dtos.NodeConfigDTO;
import org.openqa.selenium.NotFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NodeConfigLoader {
    public static NodeConfigDTO load(String filePath) throws NotFoundException {
        InputStream inputStream = NodeConfigLoader.class.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new NotFoundException("Config: " + filePath + " not found.");
        }
        BufferedReader configFile = new BufferedReader(new InputStreamReader(inputStream));
        return new Gson().fromJson(configFile, NodeConfigDTO.class);
    }
}
