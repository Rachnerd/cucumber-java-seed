package com.seed.test.utils.page;

import com.seed.test.utils.driver.LocalDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.io.File;
import java.io.IOException;

/**
 * Each container of a page extends this class and server as a wrapper around a page
 * that interacts with the page. (See examples).
 */
@ContextConfiguration(locations = "classpath:cucumber.xml")
public abstract class PageContainer extends AbstractTestNGSpringContextTests implements PageIdentity {

    public void takeScreenShot(String fileName) {
        File file = new File("screenshots/" + getClass().getSimpleName() + "/" + fileName + ".png");
        File tmpFile = LocalDriverManager.getDriver()
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tmpFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean isDisplayed();

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
