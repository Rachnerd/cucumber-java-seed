package com.seed.test.implementation.page_objects;

import com.seed.test.utils.page.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class ExamplePage extends PageObject {

    @FindBy(id = "lst-ib")
    public WebElement searchBar;

    @FindBy(id = "resultStats")
    public WebElement results;
}
