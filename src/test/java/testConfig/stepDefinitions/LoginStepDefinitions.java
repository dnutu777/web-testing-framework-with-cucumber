package testConfig.stepDefinitions;

import core.utils.ExcelReader;
import core.utils.PasswordDecrypt;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testConfig.BaseUiTest;
import testConfig.services.LoginService;
import java.util.List;

public class LoginStepDefinitions {
    private final BaseUiTest baseUiTest;

    private final List<String[]> loginErrorMessages = ExcelReader.readExcelData("src/test/java/resources/testData/login_data.xlsx", "error_messages");
    private final Logger logger = LoggerFactory.getLogger(LoginStepDefinitions.class);
    private final PasswordDecrypt passwordDecrypt;

    private final LoginService loginService;

    public LoginStepDefinitions(BaseUiTest baseUiTest) {
        this.baseUiTest = baseUiTest;
        this.loginService = new LoginService(baseUiTest.getDriverThreadLocal().get());
        this.passwordDecrypt = new PasswordDecrypt();
    }

    @When("the user clicks on the Login button from the home page")
    public void clickOnLoginButtonFromHomePageStep() {
        logger.info("Clicking on the Login button from the home page");
        loginService.clickOnLoginFromHomePage();
    }

    @When("the user clicks on the Login button from the login page")
    public void clickOnLoginButtonFromLoginPageStep() {
        logger.info("Clicking on the Login button from the login page");
        loginService.clickOnLoginFromLoginPage();
    }

    @And("the user enters the {string} email address")
    public void enterEmailAddressStep(String email) {
        logger.info("Entering email address.");
        switch (email) {
            case "user":
                loginService.enterEmail(baseUiTest.getConfig().getCredentials().get("user").toString());
                break;
            case "user2":
                loginService.enterEmail(baseUiTest.getConfig().getCredentials().get("user2").toString());
                break;
            case "user3":
                loginService.enterEmail(baseUiTest.getConfig().getCredentials().get("user3").toString());
                break;
            case "user4":
                loginService.enterEmail(baseUiTest.getConfig().getCredentials().get("user4").toString());
                break;
            default:
                loginService.enterEmail(email);
                break;
        }
    }

    @And("the user enters the {string} password")
    public void enterPasswordStep(String password) {
        logger.info("Entering the password.");
        switch (password) {
            case "password":
                loginService.enterPassword(passwordDecrypt.getPasswordDecoded(baseUiTest.getConfig().getCredentials().get("password").toString()));
                break;
            case "password2":
                loginService.enterPassword(passwordDecrypt.getPasswordDecoded(baseUiTest.getConfig().getCredentials().get("password2").toString()));
                break;
            case "password3":
                loginService.enterPassword(passwordDecrypt.getPasswordDecoded(baseUiTest.getConfig().getCredentials().get("password3").toString()));
                break;
            case "password4":
                loginService.enterPassword(passwordDecrypt.getPasswordDecoded(baseUiTest.getConfig().getCredentials().get("password4").toString()));
                break;
            default:
                loginService.enterPassword(password);
                break;
        }
    }

    @Then("the invalid email error message is received")
    public void checkTheEmailErrorMessageStep() {
        logger.info("Checking the invalid email error message.");
        Assert.assertTrue(loginService.checkEmailFieldError(loginErrorMessages.get(0)[0]));
    }

    @Then("the password length error message is received")
    public void checkThePasswordErrorMessageStep() {
        logger.info("Checking the password length error message.");
        Assert.assertTrue(loginService.checkPasswordFieldError(loginErrorMessages.get(0)[1]));
    }

    @And("the user clears the password field")
    public void clearPasswordField() {
        logger.info("Clearing the password field.");
        loginService.clearPassword();
    }

    @Then("the user is successfully logged in")
    public void userIsLoggedInStep() {
        logger.info("Checking the user is successfully logged in.");
        Assert.assertTrue(loginService.checkUserIsLoggedIn());
    }
}
