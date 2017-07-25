package nl.energiedirect.test.runner.listeners;

import nl.energiedirect.test.implementation.step_definitions.Hooks;
import nl.energiedirect.test.utils.driver.LocalDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class ErrorListener extends TestListenerAdapter {
    private final Logger log = LogManager.getLogger(getClass());

    @Override
    public void onTestFailure(ITestResult tr) {
        String stackTrace = "Scenario: " + Hooks.scenario.getName() + " failed: \n" + tr.getThrowable().toString();
        Optional<String> additionalStacktraceInfo = Arrays.stream(tr.getThrowable().getCause().getStackTrace())
                .map(StackTraceElement::toString)
                .reduce((s1, s2) -> s1 + "\n    at " + s2);
        if (additionalStacktraceInfo.isPresent()) {
            stackTrace += "\n    at " + additionalStacktraceInfo.get();
        }
        log.error(stackTrace);
        takeScreenShot(ThreadContext.get("threadId") + " " + Hooks.scenario.getName());
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    }

    private void takeScreenShot(String fileName) {
        File file = new File("screenshots/errors/" + fileName + ".png");
        File tmpFile = LocalDriverManager.getDriver()
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tmpFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
