package testConfig.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id= "email")
    private WebElement emailField;
    @FindBy(id= "password")
    private WebElement passwordField;
    @FindBy(css = "button[data-testid='login-submit']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@id='email']/following-sibling::div")
    private WebElement emailFieldError;
    @FindBy(xpath = "//input[@id='password']/following-sibling::div")
    private WebElement passwordFieldError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmailAddress(String emailAddress) {
        enterInputInField(emailField, emailAddress);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enterInputInField(passwordField, password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        clickOn(loginButton);
        return this;
    }

    public LoginPage clearPasswordField() {
        clearField(passwordField);
        return this;
    }

    public boolean checkEmailErrorMessage(String message) {
        return getTextFromElement(emailFieldError).equals(message);
    }

    public boolean checkPasswordErrorMessage(String message) {
        return getTextFromElement(passwordFieldError).equals(message);
    }
}
