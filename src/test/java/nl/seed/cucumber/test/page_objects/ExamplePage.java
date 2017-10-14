package nl.seed.cucumber.test.page_objects;

import nl.seed.cucumber.utils.page.AbstractPageObject;
import org.springframework.stereotype.Component;

@Component
public class ExamplePage extends AbstractPageObject {

    @Override
    public String[] getIdentities() {
        return new String[]{"blabla"};
    }

    @Override
    public boolean isDisplayed() {
        return true;
    }
}
