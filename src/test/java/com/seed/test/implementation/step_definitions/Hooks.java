package com.seed.test.implementation.step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import com.seed.test.utils.driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;

@Scope("cucumber-glue")
public class Hooks {

    public static Scenario scenario;
    private static ThreadLocal<Boolean> canReset = new ThreadLocal<>();
    private final Logger log = LogManager.getLogger(getClass());

    @Before
    public void test(Scenario scenario) {
        Hooks.scenario = scenario;
        log.info("--------------------");
        log.info("Scenario: " + scenario.getName());
    }


    @Before
    public void initDriver() throws MalformedURLException {
        // skip first run
        if (canReset.get() == null) {
            canReset.set(true);
        } else {
            // We need to reset the app to fix the not found element exception.
            LocalDriverManager.getDriver().resetApp();
        }
    }
}
