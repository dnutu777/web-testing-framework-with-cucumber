package testConfig.services;

import org.openqa.selenium.WebDriver;
import testConfig.pages.HomePage;
import testConfig.pages.LoginPage;
import testConfig.pages.MyNotesPage;

public class LoginService {
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final MyNotesPage myNotesPage;

    public LoginService(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.myNotesPage = new MyNotesPage(driver);
    }

    public LoginPage enterEmail(String email) {
        return loginPage.enterEmailAddress(email);
    }

    public LoginPage enterPassword(String password) {
        return loginPage.enterPassword(password);
    }

    public HomePage clickOnLoginFromHomePage() {
        return homePage.clickOnLoginButton();
    }

    public LoginPage clickOnLoginFromLoginPage() {
        return loginPage.clickOnLoginButton();
    }

    public boolean checkEmailFieldError(String expectedMessage) {
        return loginPage.checkEmailErrorMessage(expectedMessage);
    }

    public boolean checkPasswordFieldError(String expectedMessage) {
        return loginPage.checkPasswordErrorMessage(expectedMessage);
    }

    public LoginPage clearPassword() {
        return loginPage.clearPasswordField();
    }

    public boolean checkUserIsLoggedIn() {
        return myNotesPage.isLogoutButtonDisplayed();
    }
}
