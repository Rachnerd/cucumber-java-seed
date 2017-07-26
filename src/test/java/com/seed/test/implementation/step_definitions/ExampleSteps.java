package com.seed.test.implementation.step_definitions;

import com.seed.test.implementation.page_containers.ExampleContainer;
import com.seed.test.utils.navigation.Navigation;
import com.seed.test.utils.page.PageContainer;
import com.seed.test.utils.page.PageIdentifier;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class ExampleSteps implements En {
    @Autowired
    private ExampleContainer exampleContainer;
    private final Logger log = LogManager.getLogger(getClass());

    public ExampleSteps() {
        When("^I submit the following query: ([\\w- ?]+)", (String query) -> {
            log.info("When I enter the following query: " + query);
            exampleContainer.search(query);
            log.debug("Entered: " + query);
            exampleContainer.submit();
            log.debug("Submitted: " + query);
        });
        Then("^I see the results of the query", () -> {
            log.info("I see the results of the query");
            Assert.assertTrue("See the results", exampleContainer.hasResults());
        });
    }
}
