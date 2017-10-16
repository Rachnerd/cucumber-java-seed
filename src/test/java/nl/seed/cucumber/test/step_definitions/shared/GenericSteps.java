package nl.seed.cucumber.test.step_definitions.shared;

import cucumber.api.java8.En;
import nl.seed.cucumber.utils.driver.DriverUtils;
import nl.seed.cucumber.utils.driver.LocalDriverManager;
import nl.seed.cucumber.utils.navigation.Navigation;
import nl.seed.cucumber.utils.page.AbstractPageObject;
import nl.seed.cucumber.utils.page.PageIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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
        Given("^I'm at the ([\\w-]+) page", (String pageName) -> {
            log.info("Given I'm at the " + pageName + " screen");
            AbstractPageObject page = pageIdentifier.getPage(pageName);
            navigation.goToPage(page);
            Assert.assertTrue(page.isDisplayed());
            log.debug(page + " is displayed");
        });

        Then("^I see the ([\\w-]+) page", (String pageName) -> {
            log.info("Then I see the " + pageName + " screen");
            AbstractPageObject page = pageIdentifier.getPage(pageName);
            Assert.assertTrue(page.isDisplayed(), "I see the " + pageName + " screen");
            log.debug(page + " is displayed");
        });

        Then("^I take a screenshot: ([\\w-/]+)$", (String name)  -> {
            try {
                Thread.sleep(200);
                log.debug("Paused for 200ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DriverUtils.takeScreenShot(name);
            log.debug("Screenshot " + name + " taken");
        });
    }

}
