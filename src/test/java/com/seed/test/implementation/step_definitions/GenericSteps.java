package com.seed.test.implementation.step_definitions;

import com.seed.test.implementation.page_containers.ExampleContainer;
import com.seed.test.utils.page.PageContainer;
import cucumber.api.java8.En;
import com.seed.test.utils.navigation.Navigation;
import com.seed.test.utils.page.PageIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class GenericSteps implements En {
    @Autowired
    private PageIdentifier pageIdentifier;

    @Autowired
    private Navigation navigation;

    private final Logger log = LogManager.getLogger(getClass());

    public GenericSteps() {
        Given("^I'm at the ([\\w-]+) page", (String pageName) -> {
            log.info("Given I'm at the " + pageName + " screen");
            PageContainer container = pageIdentifier.getContainer(pageName);
            navigation.goToPage(container);
            Assert.assertTrue(container.isDisplayed());
        });

        When("^I enter the follow query: ([\\w- ?]+)", (String query) -> {
            ((ExampleContainer) pageIdentifier.getContainer("example")).search(query);
            Assert.assertTrue(((ExampleContainer) pageIdentifier.getContainer(("example"))).hasResults());
        });

        Then("^I see the ([\\w-]+) page", (String pageName) -> {
            log.info("Then I see the " + pageName + " screen");
            PageContainer container = pageIdentifier.getContainer(pageName);
            Assert.assertTrue("I see the " + pageName + " screen", container.isDisplayed());
            log.debug(container + " is displayed");
        });
    }

}
