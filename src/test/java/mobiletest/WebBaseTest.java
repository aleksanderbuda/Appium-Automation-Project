package mobiletest;

import appiumtests.constants.TestType;
import appiumtests.util.driver.MobileDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import appiumtests.constants.Direction;
import appiumtests.util.driver.MobileDriverService;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

import static appiumtests.constants.DriverConstants.APPIUM_DRIVER_TIMEOUT_IN_SECONDS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class WebBaseTest {
    protected MobileDriverService driverService;
    protected AppiumDriver driver;
    private final Logger logger = LogManager.getLogger(WebBaseTest.class);

    @BeforeMethod
    public void setUp() {
        MobileDriverFactory driverFactory = new MobileDriverFactory();
        driverService = driverFactory.getDriverService();
        driverService.startUpDriver(TestType.WEB);
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
                startY = (int) (size.height * 0.6);
                endY = (int) (size.height * 0.4);
                endX = startX;
                break;
            case DOWN:
                startX = size.width / 2;
                startY = (int) (size.height * 0.4);
                endY = (int) (size.height * 0.6);
                endX = startX;
                break;
            case LEFT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.6);
                endX = (int) (size.width * 0.4);
                endY = startY;
                break;
            case RIGHT:
                startY = size.height / 2;
                startX = (int) (size.width * 0.4);
                endX = (int) (size.width * 0.6);
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
            long timeoutMillis = (long) (timeout.floatValue() * 1000);
            Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }

        logger.debug("Pause is over. Keep going..");
    }
}
