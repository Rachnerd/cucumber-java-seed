package nl.seed.cucumber.test.step_definitions.shared;

import cucumber.api.java8.En;
import nl.seed.cucumber.utils.navigation.Navigation;
import nl.seed.cucumber.utils.page.AbstractPageObject;
import nl.seed.cucumber.utils.page.PageIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.testng.Assert;

@Scope("cucumber-glue")
public class GenericSteps implements En {
    @Autowired
    private PageIdentifier pageIdentifier;

    @Autowired
    private Navigation navigation;

    private final Logger log = LogManager.getLogger(getClass());

    public GenericSteps() {
        Given("^I'm at ([\\w-]+) screen", (String screenName) -> {
            log.info("Given I'm at the " + screenName + " screen");
            AbstractPageObject page = pageIdentifier.getPage(screenName);
            navigation.goToPage(page);
            Assert.assertTrue(page.isDisplayed());
            log.debug(page + " is displayed");
        });

        Then("^I see the ([\\w-]+) screen", (String screenName) -> {
            log.info("Then I see the " + screenName + " screen");
            AbstractPageObject container = pageIdentifier.getPage(screenName);
            Assert.assertTrue(container.isDisplayed(), "I see the " + screenName + " screen");
            log.debug(container + " is displayed");
        });
    }

}
