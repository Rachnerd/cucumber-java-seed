package com.seed.test.utils.page;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.seed.test.utils.driver.LocalDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = "classpath:cucumber.xml")
public abstract class PageObject extends AbstractTestNGSpringContextTests {

    public PageObject() {
        Integer implicitWaitTime = 10;
        PageFactory.initElements(new AppiumFieldDecorator(LocalDriverManager.getDriver(), implicitWaitTime,
                TimeUnit.SECONDS), this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
