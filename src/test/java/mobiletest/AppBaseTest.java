package mobiletest;

import appiumtests.constants.Direction;
import appiumtests.constants.TestType;
import appiumtests.util.driver.MobileDriverFactory;
import appiumtests.util.driver.MobileDriverService;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static appiumtests.constants.DriverConstants.APPIUM_DRIVER_TIMEOUT_IN_SECONDS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class AppBaseTest {
    protected MobileDriverService driverService;
    protected AppiumDriver driver;
    private final Logger logger = LogManager.getLogger(AppBaseTest.class);

    @BeforeMethod
    public void setUp() {
        MobileDriverFactory driverFactory = new MobileDriverFactory();
        driverService = driverFactory.getDriverService();
        driverService.startUpDriver(TestType.APP);
        driver = driverService.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(APPIUM_DRIVER_TIMEOUT_IN_SECONDS));
        logger.info("Driver started successfully for platform: {}", System.getProperty("platform", ANDROID));
    }

    protected void quitDriver() {
        if (driverService != null) {
            driverService.tearDownDriver();
            logger.info("Driver closed successfully");
        }
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

    public void swipeScreen(Direction direction) {
        Dimension size = driver.manage().window().getSize();

        int startX, startY, endX, endY;
        switch (direction) {
            case UP:
                startX = size.width / 2;
                startY = (int) (size.height * 0.8);
                endY = (int) (size.height * 0.2);
                endX = startX;
                break;
            case DOWN:
                startX = size.width / 2;
                startY = (int) (size.height * 0.2);
                endY = (int) (size.height * 0.8);
                endX = startX;
                break;
            case LEFT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.8);
                endX = (int) (size.width * 0.2);
                endY = startY;
                break;
            case RIGHT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.2);
                endX = (int) (size.width * 0.8);
                endY = startY;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        logger.debug("Executing swipe action");
        driver.perform(List.of(swipe));

    }

    public void pause(Number timeout) {
        logger.debug("Will wait for {} seconds", timeout);

        try {
            Float timeoutFloat = timeout.floatValue() * 1000.0F;
            long timeoutLong = timeoutFloat.longValue();
            Thread.sleep(timeoutLong);
        } catch (InterruptedException var4) {
            Thread.currentThread().interrupt();
        }

        logger.debug("Pause is over. Keep going..");
    }

    public static String createRandomAddress() {
        int streetNumber = new Random().nextInt(8999) + 1000; // 1000 to 9999
        String streetName = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(10).toLowerCase());
        return String.format("%d %s St", streetNumber, streetName);
    }

    public static String createRandomCardNumber() {
        return String.format("%s-%s-%s-%s",
                RandomStringUtils.randomNumeric(4),
                RandomStringUtils.randomNumeric(4),
                RandomStringUtils.randomNumeric(4),
                RandomStringUtils.randomNumeric(4));
    }
}
