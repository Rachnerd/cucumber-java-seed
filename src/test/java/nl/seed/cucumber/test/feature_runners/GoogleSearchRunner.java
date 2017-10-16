package nl.seed.cucumber.test.feature_runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class GoogleSearchRunner {
    private final Logger log = LogManager.getLogger(getClass());

    /**
     * First runner
     */
    @CucumberOptions(
            plugin = "json:target/cucumber.json",
            features = "src/test/resources/features/GoogleSearch.feature",
            glue = {
                    "nl.seed.cucumber.test.step_definitions.shared",
                    "nl.seed.cucumber.test.step_definitions.google"
            }
    )
    private class SearchOptions {
    }

    @Test(groups = {"all", "google"})
    public void runSearch() {
        log.info("Start testing all scenario's of ExampleFeature");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(SearchOptions.class);
        runner.runCukes();
    }

    @CucumberOptions(
            plugin = "json:target/cucumber.json",
            features = "src/test/resources/features/GoogleImageSearch.feature",
            glue = {
                    "nl.seed.cucumber.test.step_definitions.shared",
                    "nl.seed.cucumber.test.step_definitions.google"
            }
    )
    private class ImagesSearchOptions {
    }

    @Test(groups = {"all", "google", "google-images"})
    public void runImageSearch() {
        log.info("Start testing all scenario's of ExampleFeature");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(ImagesSearchOptions.class);
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
    private class HappyFlowOptions extends SearchOptions {
    }

    @Test(groups = {"happy-flows", "search-happy-flow"})
    public void runHappyFlow() {
        log.info("Start testing happy-flow scenario's of ExampleFeature");
        TestNGCucumberRunner runner = new TestNGCucumberRunner(HappyFlowOptions.class);
        runner.runCukes();
    }
}
