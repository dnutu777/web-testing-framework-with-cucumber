package testConfig.services;

import org.openqa.selenium.WebDriver;
import testConfig.pages.MyNotesPage;

public class ProfileSettingsService {
    private final MyNotesPage myNotesPage;

    public ProfileSettingsService(WebDriver driver) {
        this.myNotesPage = new MyNotesPage(driver);
    }

    public MyNotesPage clickOnProfileButton() {
        return (MyNotesPage) myNotesPage.clickOnProfileButton()
                          .waitForInvisibilityOfLoadingCircle();
    }
}
