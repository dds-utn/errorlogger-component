package ar.utn.frba.dds.logger.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private String filePath;

    public ConfigReader() {
        this.filePath = "loggerconfig.properties";
    }

    public Properties getProperties() throws IOException {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(input);
        }
        return prop;
    }

    public String getProperty(String key) throws IOException {
        Properties prop = getProperties();
        return prop.getProperty(key);
    }
}
