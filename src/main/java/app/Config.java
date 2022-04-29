package app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Config {

    public static String configPath = "CONFIG.properties";
    private static final Properties properties = new Properties();

    public static void load() throws IOException {
        properties.load(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(configPath)), StandardCharsets.UTF_8));
        properties.put("OS", System.getProperty("os.name"));
        properties.put("OS_ARCH", System.getProperty("os.arch"));
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}