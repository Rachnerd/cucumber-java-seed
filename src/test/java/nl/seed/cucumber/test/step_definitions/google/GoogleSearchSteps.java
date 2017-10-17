package nl.seed.cucumber.test.step_definitions.google;

import cucumber.api.java8.En;
import nl.seed.cucumber.test.page_objects.GoogleResult;
import nl.seed.cucumber.test.page_objects.GoogleSearch;
import nl.seed.cucumber.utils.driver.LocalDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class GoogleSearchSteps implements En {
    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    private GoogleSearch googleSearch;

    @Autowired
    private GoogleResult googleResult;

    private WebDriver driver = LocalDriverManager.get();

    public GoogleSearchSteps() {
        When("^I enter ([\\w-]+) in the search bar$", (String query) -> {
            log.info("I enter " + query + " in the search bar");
            googleSearch.enterSearchQuery(query);
        });

        When("^I submit the form$", () -> {
            log.info("I submit the form");
            googleSearch.submit();
        });

    }
}
