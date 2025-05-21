@regression
Feature: My Notes scenarios

  Scenario: Validations on the note fields work correctly
    When the user clicks on the Login button from the home page
    And the user enters the "user3" email address
    And the user enters the "password3" password
    And the user clicks on the Login button from the login page
    And the user clicks on the Add Note button
    And the user clicks on the Create Save button
    Then the title required error message is displayed
    And the description required error message is displayed
    When the user enters "dff" in the note title field
    Then the title length error message is displayed
    When the user enters "dff" in the note description field
    Then the description length error message is displayed
    And the user clears the "Title" note field
    And the user clears the "Description" note field
    When the user enters "longString" in the note title field
    Then the title length error message is displayed
    When the user enters "longString" in the note description field
    Then the description length error message is displayed