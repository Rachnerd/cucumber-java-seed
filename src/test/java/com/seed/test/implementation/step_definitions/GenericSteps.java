package com.seed.test.implementation.step_definitions;

import cucumber.api.java8.En;
import com.seed.test.implementation.page_containers.HomePageContainer;
import com.seed.test.utils.navigation.Navigation;
import com.seed.test.utils.page.PageContainer;
import com.seed.test.utils.page.PageIdentifier;
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
        Given("^I'm at the ([\\w-]+) screen$", (String pageName) -> {
            log.info("Given I'm at the " + pageName + " screen");
            PageContainer page = pageIdentifier.get(pageName);
            navigation.goToPage(page);
            Assert.assertTrue(page.isDisplayed(), pageName + " page loaded successfully.");
        });

        When("^I click start wizard", () -> {
            HomePageContainer page = (HomePageContainer) pageIdentifier.get("home");
            page.goToWizard();
        });

        Then("^I see the ([\\w-]+) screen$", (String pageName) -> {
            log.info("Then I see the " + pageName + " screen");
            PageContainer page = pageIdentifier.get(pageName);
            Assert.assertTrue(page.isDisplayed(), "I see the " + pageName + " screen");
            log.debug(page + " is displayed");
        });
    }

}
