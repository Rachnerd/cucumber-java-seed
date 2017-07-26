package com.seed.test.implementation.page_containers;


import com.seed.test.implementation.page_objects.ExamplePage;
import com.seed.test.utils.page.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleContainer extends PageContainer {
    @Autowired
    private ExamplePage page;

    @Override
    public String[] getIdentities() {
        return new String[]{"example"};
    }

    @Override
    public boolean isDisplayed() {
        return page.searchBar.isDisplayed();
    }

    public boolean hasResults() {
        return page.results.isDisplayed();
    }

    public void search(String query) {
        page.searchBar.sendKeys(query);
    }
    public void submit() {
        page.searchBar.submit();
    }
}
