
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/html-report"
        },
        features = "src/test/java/resources/features",
        glue = "testConfig"
)
public class  TestRunner {
}
