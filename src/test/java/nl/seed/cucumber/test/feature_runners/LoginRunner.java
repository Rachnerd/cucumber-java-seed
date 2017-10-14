package nl.seed.cucumber.test.feature_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class LoginRunner {
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * First runner
     */
    @CucumberOptions(
            plugin = "json:target/cucumber.json",
            features = "src/test/resources/features/Login.feature",
            glue = {
                    "nl.seed.cucumber.test.step_definitions.shared",
                    "nl.seed.cucumber.test.step_definitions.login"
            }
    )
    private class BaseOptions {
    }

    @Test(groups = {"all", "example"})
    public void run() {
        log.info("Start testing all scenario's of ExampleFeature");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(BaseOptions.class);
        runner.runCukes();
    }

    /**
     * Second runner
     */
    @CucumberOptions(
            tags = {
                    "@happy-flow"
            }
    )
    private class HappyFlowOptions extends BaseOptions {
    }

    @Test(groups = {"example-happy-flow"})
    public void runHappyFlow() {
        log.info("Start testing happy-flow scenario's of ExampleFeature");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(HappyFlowOptions.class);
        runner.runCukes();
    }
}
