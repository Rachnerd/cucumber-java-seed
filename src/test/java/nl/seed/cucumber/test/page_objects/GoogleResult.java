package nl.seed.cucumber.test.page_objects;

import nl.seed.cucumber.utils.page.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class GoogleResult extends AbstractPageObject {
    @FindBy(id="resultStats")
    private WebElement results;

    @Override
    public String[] getIdentities() {
        return new String[]{"google-result"};
    }

    @Override
    public boolean isDisplayed() {
        return results.isDisplayed();
    }
}
