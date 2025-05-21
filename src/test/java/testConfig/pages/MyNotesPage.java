package testConfig.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class MyNotesPage extends BasePage {
    @FindBy(linkText = "Profile")
    private WebElement profileButton;
    @FindBy(css = "button[data-testid='logout']")
    private WebElement logoutButton;
    @FindBy(css = "button[data-testid='add-new-note']")
    private WebElement addNewNoteButton;
    @FindBy(className = "card")
    private List<WebElement> notes;
    @FindBy(css = "button[data-testid='note-delete-confirm']")
    private WebElement confirmDeleteNoteButton;
    @FindBy(css = "button[data-testid='note-delete-cancel-2']")
    private WebElement cancelDeleteNoteButton;
    @FindBy(css = "button[data-testid='note-delete-cancel-1']")
    private WebElement closeDeleteNoteButton;
    @FindBy(css = "button[data-testid='note-delete']")
    private WebElement deleteNoteButton;
    @FindBy(css = "a[data-testid='note-view']")
    private WebElement viewNoteButton;

    public MyNotesPage(WebDriver driver) {
        super(driver);
    }

    public MyNotesPage clickOnProfileButton() {
        clickOn(profileButton);
        return this;
    }

    public boolean isLogoutButtonDisplayed() {
        return waitForVisibility(logoutButton).isDisplayed();
    }

    public MyNotesPage clickOnAddNoteButton() {
        clickOn(addNewNoteButton);
        return this;
    }

    public boolean checkNoteCategory(int noteId, String color) {
        return waitForVisibilityOfAllElements(notes).get(noteId).findElement(By.xpath("div/following-sibling::div")).getCssValue("background-color").contains(color);
    }

    public boolean checkNoteTitle(int noteId, String title) {
        return getTextFromElement(waitForVisibilityOfAllElements(notes).get(noteId).findElement(By.xpath("div/following-sibling::div"))).equals(title);
    }

    public boolean checkNoteDescription(int noteId, String description) {
        return getTextFromElement(waitForVisibilityOfAllElements(notes).get(noteId).findElement(By.xpath("div/following-sibling::div/following-sibling::div/p"))).equals(description);
    }

    public MyNotesPage clickOnDeleteNoteButton() {
        clickOn(deleteNoteButton);
        return this;
    }

    public MyNotesPage clickOnConfirmDeleteNoteButton() {
        clickOn(confirmDeleteNoteButton);
        return this;
    }

    public MyNotesPage clickOnCancelDeleteNoteButton() {
        clickOn(cancelDeleteNoteButton);
        return this;
    }

    public MyNotesPage clickOnCloseDeleteNoteButton() {
        clickOn(closeDeleteNoteButton);
        return this;
    }

    public MyNotesPage clickOnViewNoteButton() {
        clickOn(viewNoteButton);
        return this;
    }
}
