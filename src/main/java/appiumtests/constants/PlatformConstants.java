package appiumtests.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static appiumtests.constants.DriverConstants.ANDROID;

public class PlatformConstants {
    private final static Logger logger = LogManager.getLogger();
    public static final String MOBILE_PLATFORM_NAME = getPlatformName();

    private static String getPlatformName() {
        String platformNamePom = System.getProperty("platform");
        String platformName;

        if (platformNamePom != null)
            platformName = platformNamePom;
        else {
            logger.warn("The Maven Profile is missing the platform configuration.");
            logger.warn("The default platform '{}' will be enabled for this run.", ANDROID);
            platformName = ANDROID;
        }

        return platformName;
    }
}
