package testConfig.services;

import org.openqa.selenium.WebDriver;
import testConfig.pages.NoteDetailsPage;

public class NoteDetailsService {
    private final NoteDetailsPage noteDetailsPage;

    public NoteDetailsService(WebDriver driver) {
        this.noteDetailsPage = new NoteDetailsPage(driver);
    }

    public NoteDetailsPage clickOnCreateSaveButton() {
        return (NoteDetailsPage) noteDetailsPage.clickOnCreateSaveButton()
                              .waitForInvisibilityOfLoadingCircle();
    }

    public boolean checkTitleErrorMessage(String message) {
        return noteDetailsPage.checkTitleErrorMessage(message);
    }

    public boolean checkDescriptionErrorMessage(String message) {
        return noteDetailsPage.checkDescriptionErrorMessage(message);
    }

    public NoteDetailsPage enterTitle(String title) {
        return noteDetailsPage.enterTitle(title);
    }

    public NoteDetailsPage enterDescription(String description) {
        return noteDetailsPage.enterDescription(description);
    }

    public NoteDetailsPage clearTitle() {
        return noteDetailsPage.clearTitle();
    }

    public NoteDetailsPage clearDescription() {
        return noteDetailsPage.clearDescription();
    }

    public NoteDetailsPage selectCategory(String category) {
        return noteDetailsPage.selectCategory(category);
    }
}
