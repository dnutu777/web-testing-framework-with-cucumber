@regression
Feature: Delete scenarios

  @smoke
  Scenario: The Cancel and Close buttons in the delete confirmation prompt work correctly
    When the user clicks on the Login button from the home page
    And the user enters the "user4" email address
    And the user enters the "password4" password
    And the user clicks on the Login button from the login page
    And the user clicks on the Profile button