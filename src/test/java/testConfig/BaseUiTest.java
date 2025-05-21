package testConfig;

import core.config.Config;
import core.config.WebDriverConfig;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.URISyntaxException;

public class BaseUiTest {
    @Getter
    private ThreadLocal<WebDriver> driverThreadLocal;
    private WebDriverConfig webDriverConfig;
    @Getter
    private Config config;

    public BaseUiTest() {
        this.driverThreadLocal = new ThreadLocal<>();
        this.config = new Config();
        this.webDriverConfig = new WebDriverConfig(config);
    }

    public void setUp() throws IOException, URISyntaxException {
        this.driverThreadLocal.set(webDriverConfig.createDriver());
    }

    public void tearDown() {
        if (this.driverThreadLocal.get() != null) {
            this.driverThreadLocal.get().quit();
            this.driverThreadLocal.remove();
            this.driverThreadLocal = null;
        }
        this.webDriverConfig = null;
        this.config = null;
    }

    public void openApplication() {
        String webAppUrl = config.getProperty("webAppUrl");
        if (webAppUrl == null || webAppUrl.isEmpty()) {
            throw new IllegalArgumentException("Web application URL is not configured");
        }
        this.driverThreadLocal.get().get(webAppUrl);
    }
}
