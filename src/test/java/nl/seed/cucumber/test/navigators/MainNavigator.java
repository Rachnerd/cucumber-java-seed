package nl.seed.cucumber.test.navigators;

import nl.seed.cucumber.utils.page.AbstractPageObject;
import nl.seed.cucumber.utils.navigation.Navigator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
class MainNavigator implements Navigator {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public boolean goTo(AbstractPageObject page) {
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
