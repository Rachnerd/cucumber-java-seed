package nl.seed.cucumber.test.step_definitions.shared;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import nl.seed.cucumber.utils.driver.DriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class Hooks {

    private static Scenario scenario;

    private final Logger log = LogManager.getLogger(getClass());

    private static ThreadLocal<Boolean> isRunning = new ThreadLocal<>();

    /**
     * Runs 1 time only.
     */
    @Before
    public void firstRun() {
        if (isRunning.get() != null) {
            return;
        }
        isRunning.set(true);
    }

    @Before
    public void printNextScenario(Scenario scenario) {
        Hooks.scenario = scenario;
        log.info("--------------------");
        log.info("Scenario: " + scenario.getName());
    }

    @After
    public void takeScreenshotIfScenarioFailed() {
        if (scenario.getStatus().equals("failed")) {
            DriverUtils.takeScreenShot("errors/" + ThreadContext.get("threadId") + " " + Hooks.scenario.getName());
            log.error("Screenshot created for failed scenario " + scenario.getName());
        }
    }
}
