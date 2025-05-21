@regression
Feature: Login tests scenarios

  Scenario: Validations on the login fields work correctly
    When the user clicks on the Login button from the home page
    And the user enters the "asdfasdfas" email address
    And the user clicks on the Login button from the login page
    Then the invalid email error message is received
    When the user enters the "df6yt" password
    Then the password length error message is received
    And the user clears the password field
    When the user enters the "dfddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" password
    Then the password length error message is received

  @smoke
  Scenario: The user can successfully log in
    When the user clicks on the Login button from the home page
    And the user enters the "user" email address
    And the user enters the "password" password
    And the user clicks on the Login button from the login page
    Then the user is successfully logged in