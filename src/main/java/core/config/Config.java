package core.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class Config {
    private Map<String, Object> config;
    private static Config instance;

    public Config() {
        loadConfig();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private InputStream getResourceAsStream(String resourceName) {
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            System.err.println("Failed to find resource: " + resourceName);
        }
        return resourceStream;
    }

    public String getProperty(String key) {
        return config.get(key).toString();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCredentials() {
        Object credentials = config.get("credentials");
        if (credentials instanceof Map) {
            return (Map<String, Object>) credentials;
        } else {
            return new LinkedHashMap<>();
        }
    }

    private void loadConfig() {
        Yaml yaml = new Yaml();
        String env;

        try (InputStream commonInputStream = getResourceAsStream("yamlFiles/common.yaml")) {
            if (commonInputStream == null) {
                throw new IllegalArgumentException("Configuration file 'common.yaml' not found");
            }
            Map<String, Object> commonConfig = yaml.load(commonInputStream);

            env = System.getProperty("env", (String) commonConfig.getOrDefault("env", "dev"));
            try (InputStream envInputStream = getResourceAsStream("yamlFiles/config/config." + env + ".yaml")) {
                if (envInputStream == null) {
                    throw new IllegalArgumentException("Environment-specific configuration file not found for environment: " + env);
                }
                Map<String, Object> envConfig = yaml.load(envInputStream);

                commonConfig.putAll(envConfig);

                String browser = System.getProperty("browser");
                String headless = System.getProperty("headless");
                String useGrid = System.getProperty("useGrid");

                if (browser != null) {
                    commonConfig.put("browser", browser);
                }
                if (headless != null) {
                    commonConfig.put("headless", Boolean.parseBoolean(headless));
                }
                if (useGrid != null) {
                    commonConfig.put("useGrid", Boolean.parseBoolean(useGrid));
                }

                this.config = commonConfig;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }
}
