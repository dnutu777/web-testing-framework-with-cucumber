# Web Testing Framework v2

A scalable and modular Selenium-Cucumber test automation framework using Java, Maven, and JUnit. It supports multi-browser testing, environment-specific configuration, Selenium Grid integration, and retry logic for failed scenarios.

## 📁 Project Structure

```
.
├── pom.xml                      # Maven project descriptor
├── src/
│   ├── main/java/
│   │   └── core/
│   │       ├── config/         # YAML-based configuration and WebDriver setup
│   │       └── utils/          # Utilities (e.g., ExcelReader, PasswordDecrypt)
│   └── test/java/
│       ├── TestRunner.java     # Cucumber test runner
│       └── testConfig/
│           ├── hooks/          # Cucumber hooks (setup, teardown, retry)
│           ├── pages/          # Page Object Models
│           └── BaseUiTest.java # Base class for UI tests
└── resources/
    └── yamlFiles/              # Config files (common.yaml, config.dev.yaml, etc.)
```

## ⚙️ Configuration

### YAML-Based
- Located in `resources/yamlFiles/`
  - `common.yaml` contains default/global values
  - `config.{env}.yaml` contains environment-specific overrides (e.g., dev, qa, prod)
- Loaded via `Config.java`

### Runtime Parameters
You can override configuration properties using system properties:
```bash
-Denv=qa -Dbrowser=chrome -Dheadless=true -DuseGrid=true
```

### Supported Browsers
- Chrome
- Firefox
- Edge
- Safari

### Selenium Grid Support
- Enable with `-DuseGrid=true` and specify `gridUrl` in YAML

## 🚀 Running Tests

### Prerequisites
- Java 21+
- Maven
- ChromeDriver/GeckoDriver or compatible WebDriver (automatically managed by WebDriverManager)
- Set ENCRYPTION_KEY=secret in Environment variables then add it to path

### Command
```bash
mvn clean test -Denv=dev -Dbrowser=chrome
```

### Parallel Execution
Enabled via Maven Surefire Plugin and Cucumber tags. Configure in `pom.xml`.

## 🔁 Retry Logic

Implemented in `Hooks.java`:
- Automatically retries failed scenarios up to 3 times.
- Includes screenshot capturing and Log4j2 logging configuration.

## 📸 Reporting
- Generates Cucumber HTML and JSON reports in `target/cucumber-reports/`

## 🛠️ Utilities
- `ExcelReader.java`: Read data from Excel files.
- `PasswordDecrypt.java`: Custom decryption logic (if used with encrypted credentials).


### 🏷️ Running Tests by Tags
You can run specific test scenarios by using Cucumber tags:

```bash
mvn test -Denv=dev -Dbrowser=chrome -Dcucumber.filter.tags="@login"
```

You can also combine tags:
- Run scenarios with both `@smoke` and `@regression`:
  ```bash
  -Dcucumber.filter.tags="@smoke and @regression"
  ```

- Run scenarios with either `@smoke` or `@regression`:
  ```bash
  -Dcucumber.filter.tags="@smoke or @regression"
  ```

- Exclude a tag:
  ```bash
  -Dcucumber.filter.tags="not @wip"
  ```
