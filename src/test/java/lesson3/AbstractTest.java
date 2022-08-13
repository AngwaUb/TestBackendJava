package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties properties = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String urlBase;
    private static String hash;
    private static String username;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        properties.load(configFile);

        apiKey = properties.getProperty("apiKey");
        urlBase = properties.getProperty("basicUrl");
        hash = properties.getProperty("hash");
        username = properties.getProperty("username");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getUrlBase() {
        return urlBase;
    }

    public static String getHash() {
        return hash;
    }

    public static String getUsername() {
        return username;
    }
}
