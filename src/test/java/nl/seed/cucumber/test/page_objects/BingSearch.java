package nl.seed.cucumber.test.page_objects;

import nl.seed.cucumber.utils.driver.DriverUtils;
import nl.seed.cucumber.utils.page.AbstractPageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class BingSearch extends AbstractPageObject {
    @FindBy(id="sb_form_q")
    public WebElement searchBar;

    public void enterSearchQuery(String query) {
        searchBar.sendKeys(query);
    }

    public void submit() {
        searchBar.sendKeys(Keys.RETURN);
    }

    @Override
    public String[] getIdentities() {
        return new String[]{"bing-search"};
    }

    @Override
    public boolean isDisplayed() {
        return searchBar.isDisplayed();
    }
}
