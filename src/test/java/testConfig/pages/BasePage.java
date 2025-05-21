package testConfig.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    @FindBy(className = "visually-hidden")
    private List<WebElement> loading;

    protected WebDriverWait webDriverWait;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }

    protected void clickOn(WebElement webElement) {
        try {
            waitForElementToBeClickable(webElement).click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", waitForElementToBeClickable(webElement));
        }
    }

    protected void enterInputInField(WebElement webElement, String input) {
        waitForVisibility(webElement).sendKeys(input);
    }

    protected void clearField(WebElement webElement) {
        waitForVisibility(webElement).clear();
    }

    protected String getTextFromElement(WebElement webElement) {
        String text = waitForVisibility(webElement).getText();
        if(text.isEmpty()) {
            text = webElement.getAttribute("value");
        }
        return text;
    }

    protected WebElement waitForVisibility(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitForElementToBeClickable(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected Boolean waitForInvisibilityOfElement(WebElement webElement) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElements) {
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void waitForElementWithPolling(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public BasePage waitForInvisibilityOfLoadingCircle() {
        if(!loading.isEmpty()) {
            webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(loading));
        }
        return this;
    }
}
