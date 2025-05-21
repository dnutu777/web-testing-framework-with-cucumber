package testConfig.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(linkText = "Login")
    private WebElement loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickOnLoginButton() {
        clickOn(loginButton);
        return this;
    }
}
