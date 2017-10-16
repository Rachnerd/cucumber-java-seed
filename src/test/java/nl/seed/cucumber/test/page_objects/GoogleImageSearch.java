package nl.seed.cucumber.test.page_objects;

import nl.seed.cucumber.utils.page.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class GoogleImageSearch extends AbstractPageObject {
    @FindBy(id="gs_si0")
    private WebElement imageSearchIcon;

    @Override
    public String[] getIdentities() {
        return new String[]{"google-image-search"};
    }

    @Override
    public boolean isDisplayed() {
        return imageSearchIcon.isDisplayed();
    }
}
