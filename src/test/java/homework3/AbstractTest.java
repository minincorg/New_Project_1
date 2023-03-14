package homework3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void intTest() throws IOException{
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");
    }

    public static String getApiKey(){
        return apiKey;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

}
