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
        /*
         * Do something only once
         */
    }

    @Before
    public void printNextScenario(Scenario scenario) {
        Hooks.scenario = scenario;
        log.info("--------------------");
        log.info("Scenario: " + scenario.getName());
    }

    @After
    public void takeScreenshot(Scenario scenario) {
        boolean hasFailed = scenario.getStatus().equals("failed");
        String logMessage = "Screenshot created for " + scenario.getStatus() + " scenario " + scenario.getName();
        String fileName = ThreadContext.get("threadId") + " " + Hooks.scenario.getName();
        if (hasFailed) {
            DriverUtils.takeScreenShot("errors/" + fileName);
            log.error(logMessage);
        }else {
            DriverUtils.takeScreenShot("successes/" + fileName);
            log.info(logMessage);
        }
    }
}
