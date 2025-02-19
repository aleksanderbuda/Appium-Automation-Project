package appiumtests.gui.app.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class AppHeaderBase {

    protected AppiumDriver driver;

    public AppHeaderBase(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void openMenu();

    public abstract void tapSortButton();

    public abstract void tapCart();

    public abstract boolean isBannerVisible();
}