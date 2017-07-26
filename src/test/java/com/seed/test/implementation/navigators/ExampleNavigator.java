package com.seed.test.implementation.navigators;

import com.seed.test.implementation.page_containers.ExampleContainer;
import com.seed.test.utils.driver.DriverUtils;
import com.seed.test.utils.navigation.Navigator;
import com.seed.test.utils.page.PageContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
class ExampleNavigator implements Navigator {

    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public boolean goTo(PageContainer page) {
        if(page instanceof ExampleContainer) {
            DriverUtils.get("https://www.google.com");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
