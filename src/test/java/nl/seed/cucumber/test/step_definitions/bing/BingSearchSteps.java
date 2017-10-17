package nl.seed.cucumber.test.step_definitions.bing;

import cucumber.api.java8.En;
import nl.seed.cucumber.test.page_objects.BingSearch;
import nl.seed.cucumber.test.page_objects.GoogleResult;
import nl.seed.cucumber.test.page_objects.GoogleSearch;
import nl.seed.cucumber.utils.driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class BingSearchSteps implements En {
    private final Logger log = LogManager.getLogger(getClass());


    @Autowired
    BingSearch bingSearch;

    public BingSearchSteps() {
        When("^I submit the form$", () -> {
            // Write code here that turns the phrase above into concrete actions
            log.info("I submit the form");
            bingSearch.submit();
        });
        When("^I enter ([\\w-]+) in the search bar$", (String query) -> {
            log.info("I enter " + query + " in the search bar");
            // Write code here that turns the phrase above into concrete actions
            bingSearch.enterSearchQuery(query);
        });

    }
}
