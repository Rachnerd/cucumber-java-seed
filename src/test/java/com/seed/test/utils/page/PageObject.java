package com.seed.test.utils.page;

import com.seed.test.utils.driver.LocalDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:cucumber.xml")
public abstract class PageObject extends AbstractTestNGSpringContextTests {

    public PageObject() {
        PageFactory.initElements(LocalDriverManager.getDriver(), this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
