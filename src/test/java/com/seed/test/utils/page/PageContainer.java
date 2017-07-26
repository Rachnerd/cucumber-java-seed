package com.seed.test.utils.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Each container of a page extends this class and server as a wrapper around a page
 * that interacts with the page. (See examples).
 */
@ContextConfiguration(locations = "classpath:cucumber.xml")
public abstract class PageContainer extends AbstractTestNGSpringContextTests implements PageIdentity {
    private final Logger log = LogManager.getLogger(getClass());

    public abstract boolean isDisplayed();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
