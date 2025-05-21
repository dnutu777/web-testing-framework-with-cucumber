package testConfig.stepDefinitions;

import core.utils.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testConfig.BaseUiTest;
import testConfig.services.MyNotesService;
import testConfig.services.NoteDetailsService;

import java.util.List;

public class MyNotesStepDefinitions {
    private final List<String[]> notesErrorMessages = ExcelReader.readExcelData("src/test/java/resources/testData/notes_data.xlsx", "error_messages");

    private final Logger logger = LoggerFactory.getLogger(MyNotesStepDefinitions.class);

    private final MyNotesService myNotesService;
    private final NoteDetailsService noteDetailsService;

    public MyNotesStepDefinitions(BaseUiTest baseUiTest) {
        this.myNotesService = new MyNotesService(baseUiTest.getDriverThreadLocal().get());
        this.noteDetailsService = new NoteDetailsService(baseUiTest.getDriverThreadLocal().get());
    }

    @When("the user clicks on the Add Note button")
    public void userClicksOnAddNoteButtonStep() {
        logger.info("Clicking on the Add Note button.");
        myNotesService.clickOnAddNoteButton();
    }

    @When("the user clicks on the Create Save button")
    public void userClicksOnCreateSaveButtonStep() {
        logger.info("Clicking on the Create Save button.");
        noteDetailsService.clickOnCreateSaveButton();
    }

    @Then("the title required error message is displayed")
    public void checkTitleRequiredErrorMessageStep() {
        logger.info("Checking the title required error message.");
        Assert.assertTrue(noteDetailsService.checkTitleErrorMessage(notesErrorMessages.get(0)[0]));
    }

    @Then("the description required error message is displayed")
    public void checkDescriptionRequiredErrorMessageStep() {
        logger.info("Checking the description required error message.");
        Assert.assertTrue(noteDetailsService.checkDescriptionErrorMessage(notesErrorMessages.get(0)[1]));
    }

    @Then("the title length error message is displayed")
    public void checkTitleLengthErrorMessageStep() {
        logger.info("Checking the title length error message.");
        Assert.assertTrue(noteDetailsService.checkTitleErrorMessage(notesErrorMessages.get(0)[2]));
    }

    @Then("the description length error message is displayed")
    public void checkDescriptionLengthErrorMessageStep() {
        logger.info("Checking the description length error message.");
        Assert.assertTrue(noteDetailsService.checkDescriptionErrorMessage(notesErrorMessages.get(0)[3]));
    }

    @When("the user enters {string} in the note title field")
    public void enterInputInNoteTitleFieldStep(String input) {
        logger.info("Entering a note title.");
        if (input.equals("longString")) {
            noteDetailsService.enterTitle("a".repeat(101));
        } else {
            noteDetailsService.enterTitle(input);
        }
    }

    @When("the user enters {string} in the note description field")
    public void enterInputInNoteDescriptionFieldStep(String input) {
        logger.info("Entering a note description.");
        if (input.equals("longString")) {
            noteDetailsService.enterDescription("a".repeat(10001));
        } else {
            noteDetailsService.enterDescription(input);
        }
    }

    @And("the user clears the {string} note field")
    public void clearNoteFieldStep(String field) {
        if (field.equals("Title")) {
            logger.info("Clearing the note title field.");
            noteDetailsService.clearTitle();
        } else {
            logger.info("Clearing the note description field.");
            noteDetailsService.clearDescription();
        }
    }
}
