package com.seed.test.utils.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class can be called to match Gherkin page names to page containers.
 * It automatically picks up every class that has a PageIdentity, which contains
 * an array of names used in Gherkin features (see examples).
 */
@Component
@ContextConfiguration("classpath*:cucumber.xml")
public class PageIdentifier {
    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    List<PageIdentity> pages;

    /**
     * Retrieves page object based on the name given in the Gherkin features.
     */
    public PageContainer get(String pageName) {
        Optional<PageIdentity> optionalPage = pages
                .stream()
                .filter(page ->
                        Arrays.stream(page.getIdentities())
                                .anyMatch(identity -> identity.equals(pageName))
                )
                .findFirst();

        if (!optionalPage.isPresent()) {
            throw new RuntimeException("Page with name " + pageName + " does not exist");
        } else {
            PageIdentity page = optionalPage.get();
            if(page instanceof PageContainer) {
                return (PageContainer) page;
            }else {
                throw new RuntimeException("Found page is not of type PageContainer");
            }
        }
    }
}
