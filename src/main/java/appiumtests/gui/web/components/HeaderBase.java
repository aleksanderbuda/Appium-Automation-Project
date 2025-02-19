package appiumtests.gui.web.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class HeaderBase {

    protected AppiumDriver driver;

    public HeaderBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void openMenu();

    public abstract void tapCart();
}