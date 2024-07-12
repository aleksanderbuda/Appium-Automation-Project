package appiumtests.gui.web.pages.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public abstract class SearchResultPageBase {

    protected AndroidDriver driver;

    public SearchResultPageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public abstract void tapFirstResult();

    public abstract void tapFilters();

    public abstract void tapResultByIndex(int index);

    public abstract boolean isPageOpened();

    public abstract boolean isBottomResultCountVisible();




}
