package nl.energiedirect.test.implementation.navigators;

import nl.energiedirect.test.utils.navigation.Navigator;
import nl.energiedirect.test.utils.page.PageContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
class HomeNavigator implements Navigator {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public boolean goTo(PageContainer page) {
        return true;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
