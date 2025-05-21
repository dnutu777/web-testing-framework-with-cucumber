package testConfig.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import testConfig.BaseUiTest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record Hooks(BaseUiTest baseUiTest) {
    private static final Map<String, Integer> retryCountMap = new HashMap<>();
    private static final int MAX_RETRY_COUNT = 3;

    @Before
    public void setUp(Scenario scenario) throws IOException, URISyntaxException {
        addToRetryMap(scenario);
        loadLoggingConfiguration();
        baseUiTest.setUp();
        baseUiTest.openApplication();
    }

    @After
    public void tearDown(Scenario scenario) {
        baseUiTest.tearDown();
        retryScenario(scenario);
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) baseUiTest.getDriverThreadLocal().get();
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Screenshot");
        }
    }

    private void loadLoggingConfiguration() {
        try {
            URI configUri = Objects.requireNonNull(getClass().getClassLoader().getResource("log4j2.xml")).toURI();
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            context.setConfigLocation(configUri);
            context.reconfigure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToRetryMap(Scenario scenario) {
        String scenarioName = scenario.getName();
        if (!retryCountMap.containsKey(scenarioName)) {
            retryCountMap.put(scenarioName, 0);
        }
    }

    public void retryScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String scenarioName = scenario.getName();
            int currentRetryCount = retryCountMap.get(scenarioName);

            if (currentRetryCount < MAX_RETRY_COUNT) {
                retryCountMap.put(scenarioName, currentRetryCount + 1);
                System.out.println("Retrying scenario: " + scenarioName + ", attempt " + (currentRetryCount + 1));

                scenario.log("Retrying failed scenario attempt " + (currentRetryCount + 1) + " of " + MAX_RETRY_COUNT);
                throw new RuntimeException("Retrying scenario: " + scenarioName);
            } else {
                retryCountMap.remove(scenarioName);
                System.out.println("Scenario failed after " + MAX_RETRY_COUNT + " attempts: " + scenarioName);
            }
        } else {
            retryCountMap.remove(scenario.getName());
        }
    }
}
