package placeholderPackageName.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Reader {
    public static String getAppiumConfig(String propertyName) {
        return getPropertyValue("appium.properties", propertyName);
    }

    public static String getDeviceConfig(String propertyName) {
        return getPropertyValue("device.properties", propertyName);
    }

    private static String getPropertyValue(String filename, String propertyName) {
        String propertyValue = null;

        try (InputStream inputStream = Reader.class.getClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return propertyValue;
    }
}
