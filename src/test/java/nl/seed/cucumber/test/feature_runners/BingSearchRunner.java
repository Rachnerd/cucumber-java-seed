package nl.seed.cucumber.test.feature_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class BingSearchRunner {
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * First runner
     */
    @CucumberOptions(
            plugin = "json:target/cucumber.json",
            features = "src/test/resources/features/BingSearch.feature",
            glue = {
                    "nl.seed.cucumber.test.step_definitions.shared",
                    "nl.seed.cucumber.test.step_definitions.bing"
            }
    )
    private class SearchOptions {
    }

    @Test(groups = {"all", "bing"})
    public void runSearch() {
        log.info("Start testing all scenario's of G");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(SearchOptions.class);
        runner.runCukes();
    }
}
