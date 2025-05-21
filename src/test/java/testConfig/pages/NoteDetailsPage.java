package testConfig.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NoteDetailsPage extends BasePage {
    @FindBy(id = "title")
    private WebElement titleField;
    @FindBy(id = "description")
    private WebElement descriptionField;
    @FindBy(css = "button[data-testid='note-submit']")
    private WebElement createSaveButton;
    @FindBy(id = "category")
    private WebElement noteCategory;

    public NoteDetailsPage(WebDriver driver) {
        super(driver);
    }

    public NoteDetailsPage clickOnCreateSaveButton() {
        clickOn(createSaveButton);
        return this;
    }

    public boolean checkTitleErrorMessage(String message) {
        return getTextFromElement(titleField.findElement(By.xpath("following-sibling::div"))).equals(message);
    }

    public boolean checkDescriptionErrorMessage(String message) {
        return getTextFromElement(descriptionField.findElement(By.xpath("following-sibling::div"))).equals(message);
    }

    public NoteDetailsPage enterTitle(String value) {
        enterInputInField(titleField, value);
        return this;
    }

    public NoteDetailsPage enterDescription(String value) {
        enterInputInField(descriptionField, value);
        return this;
    }

    public NoteDetailsPage clearTitle() {
        clearField(titleField);
        return this;
    }

    public NoteDetailsPage clearDescription() {
        clearField(descriptionField);
        return this;
    }

    public NoteDetailsPage selectCategory(String category) {
        Select noteCategorySelect = new Select(this.noteCategory);
        noteCategorySelect.selectByValue(category);
        return this;
    }
}
