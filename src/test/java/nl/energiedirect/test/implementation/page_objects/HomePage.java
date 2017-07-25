package nl.energiedirect.test.implementation.page_objects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import nl.energiedirect.test.utils.page.PageObject;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends PageObject {

    @AndroidFindBy(accessibility = "wizardExampleButton")
    public MobileElement wizardButton;

}
