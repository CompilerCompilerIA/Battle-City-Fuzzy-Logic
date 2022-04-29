package app;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class Translate {

    private static final Properties properties = new Properties();

    public static void load() throws IOException {
        String path = String.format("%s%s", "translate/", Config.get("TRANSLATE"));
        properties.load(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(path)), StandardCharsets.UTF_8));
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
