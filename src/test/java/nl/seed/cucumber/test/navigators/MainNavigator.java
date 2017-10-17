package nl.seed.cucumber.test.navigators;

import nl.seed.cucumber.test.page_objects.BingSearch;
import nl.seed.cucumber.test.page_objects.GoogleImageSearch;
import nl.seed.cucumber.test.page_objects.GoogleSearch;
import nl.seed.cucumber.utils.driver.DriverUtils;
import nl.seed.cucumber.utils.page.AbstractPageObject;
import nl.seed.cucumber.utils.navigation.Navigator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainNavigator implements Navigator, Routes {

    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    private GoogleSearch googleSearch;

    @Override
    public boolean goTo(AbstractPageObject page) {
        if(page instanceof GoogleSearch) {
            DriverUtils.get(HOME);
            return true;
        }
        if(page instanceof GoogleImageSearch) {
            DriverUtils.get(HOME);
            googleSearch.goToImageSearch();
            return true;
        }
        if(page instanceof BingSearch) {
            DriverUtils.get(HOME);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
