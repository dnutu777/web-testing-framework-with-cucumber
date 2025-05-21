package testConfig.stepDefinitions;

import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testConfig.BaseUiTest;
import testConfig.services.ProfileSettingsService;

public class ProfileSettingsStepDefinitions {
    private final Logger logger = LoggerFactory.getLogger(ProfileSettingsStepDefinitions.class);

    private final ProfileSettingsService profileSettingsService;

    public ProfileSettingsStepDefinitions(BaseUiTest baseUiTest) {
        profileSettingsService = new ProfileSettingsService(baseUiTest.getDriverThreadLocal().get());
    }

    @When("the user clicks on the Profile button")
    public void userClicksOnProfileButtonStep() {
        logger.info("Clicking on the Profile button.");
        profileSettingsService.clickOnProfileButton();
    }
}
