@regression
Feature: Profile Settings scenarios

  Scenario: The user can successfully update his password
    When the user clicks on the Login button from the home page
    And the user enters the "user4" email address
    And the user enters the "password4" password
    And the user clicks on the Login button from the login page
    And the user clicks on the Profile button
