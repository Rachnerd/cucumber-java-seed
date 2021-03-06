package com.seed.test.implementation.page_containers;


import com.seed.test.implementation.page_objects.HomePage;
import com.seed.test.utils.page.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePageContainer extends PageContainer {
    @Autowired
    private HomePage page;

    public void goToWizard() {
        page.wizardButton.tap(1, 1);
    }

    @Override
    public String[] getIdentities() {
        return new String[]{"home"};
    }

    @Override
    public boolean isDisplayed() {
        return page.wizardButton.isDisplayed();
    }
}
