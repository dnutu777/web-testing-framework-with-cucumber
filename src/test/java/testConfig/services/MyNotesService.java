package testConfig.services;

import org.openqa.selenium.WebDriver;
import testConfig.pages.MyNotesPage;

public class MyNotesService {
    private final MyNotesPage myNotesPage;

    public MyNotesService(WebDriver driver) {
        this.myNotesPage = new MyNotesPage(driver);
    }

    public MyNotesPage clickOnAddNoteButton() {
        return myNotesPage.clickOnAddNoteButton();
    }

    public MyNotesPage clickOnDeleteNoteButton() {
        return myNotesPage.clickOnDeleteNoteButton();
    }

    public MyNotesPage clickOnCancelDeleteNoteButton() {
        return myNotesPage.clickOnCancelDeleteNoteButton();
    }

    public MyNotesPage clickOnCloseDeleteNoteButton() {
        return myNotesPage.clickOnCloseDeleteNoteButton();
    }

    public MyNotesPage clickOnViewNoteButton() {
        return (MyNotesPage) myNotesPage.clickOnViewNoteButton()
                                        .waitForInvisibilityOfLoadingCircle();

    }

    public MyNotesPage clickOnConfirmDeleteNoteButton() {
        return (MyNotesPage) myNotesPage.clickOnConfirmDeleteNoteButton()
                          .waitForInvisibilityOfLoadingCircle();

    }

    public boolean checkNoteCategory(int noteId, String color) {
        return myNotesPage.checkNoteCategory(noteId, color);
    }

    public boolean checkNoteTitle(int noteId, String color) {
        return myNotesPage.checkNoteTitle(noteId, color);
    }

    public boolean checkNoteDescription(int noteId, String color) {
        return myNotesPage.checkNoteDescription(noteId, color);
    }
}
