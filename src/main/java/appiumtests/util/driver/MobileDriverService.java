package appiumtests.util.driver;

import appiumtests.constants.TestType;
import io.appium.java_client.android.AndroidDriver;

public interface MobileDriverService {
    void startUpDriver(TestType testType);
    void tearDownDriver();
    AndroidDriver getDriver();
}