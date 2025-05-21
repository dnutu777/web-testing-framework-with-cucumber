package core.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class WebDriverConfig {
    private final String browser;
    private final String headless;
    private final String useGrid;
    private final String gridUrl;

    public WebDriverConfig(Config config) {
        this.browser = config.getProperty("browser");
        this.headless = config.getProperty("headless");
        this.useGrid = config.getProperty("useGrid");
        this.gridUrl = config.getProperty("gridUrl");
    }

    public WebDriver createDriver() throws IOException, URISyntaxException {
        return Boolean.parseBoolean(useGrid) ? createRemoteDriver() : createLocalDriver();
    }

    private WebDriver createRemoteDriver() throws IOException, URISyntaxException {
        WebDriver driver = switch (browser) {
            case "chrome" -> new RemoteWebDriver(new URI(gridUrl).toURL(), getChromeOptions());
            case "firefox" -> new RemoteWebDriver(new URI(gridUrl).toURL(), getFirefoxOptions());
            case "edge" -> new RemoteWebDriver(new URI(gridUrl).toURL(), getEdgeOptions());
            case "safari" -> new RemoteWebDriver(new URI(gridUrl).toURL(), null);
            default ->
                    throw new IllegalArgumentException("Browser [" + browser + "] is not supported when using Selenium Grid.");
        };
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver createLocalDriver() {
        WebDriver driver = switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(getChromeOptions());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(getFirefoxOptions());
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver(getEdgeOptions());
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                yield new SafariDriver();
            }
            default -> throw new IllegalArgumentException("Browser [" + browser + "] is not supported.");
        };

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }
}
