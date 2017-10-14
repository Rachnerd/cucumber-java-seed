package nl.seed.cucumber.utils.page;

import nl.seed.cucumber.utils.driver.LocalDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:cucumber.xml")
public abstract class AbstractPageObject extends AbstractTestNGSpringContextTests implements PageIdentity {

    public AbstractPageObject() {
        PageFactory.initElements(LocalDriverManager.get(), this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public abstract boolean isDisplayed();
}
