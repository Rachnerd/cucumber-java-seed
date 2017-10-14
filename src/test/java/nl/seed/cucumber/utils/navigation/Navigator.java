package nl.seed.cucumber.utils.navigation;

import nl.seed.cucumber.utils.page.AbstractPageObject;

import java.util.Arrays;

/**
 * Interface executed by the Navigation class and implemented at implementation level.
 */
public interface Navigator {
    boolean goTo(AbstractPageObject page);

    default String createRoute(String... routes) {
        return Arrays.stream(routes)
                .reduce((routes1, routes2) -> routes1 + "/" + routes2)
                .orElse("Something went wrong");
    }
}
