package nl.energiedirect.test.utils.navigation;

import nl.energiedirect.test.utils.page.PageContainer;
import nl.energiedirect.test.utils.page.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Navigation {
    private final Logger log = LogManager.getLogger(Navigation.class);

    @Autowired
    List<Navigator> navigatorList;

    public boolean goToPage(PageContainer page) {
        log.debug("Start navigators to " + page);

        Optional<Navigator> navigator = navigatorList
                .stream()
                .filter((nav) -> nav.goTo(page))
                .findFirst();

        boolean navigationHandled = navigator.isPresent();
        if (!navigationHandled) {
            log.error("Navigating to " + page + " not implemented");
        }

        return navigationHandled;
    }
}
