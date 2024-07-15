package appiumtests.gui.web.pages.ios;

import appiumtests.gui.web.pages.common.FiltersPageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class FiltersPage extends FiltersPageBase {

    @FindBy(xpath = "//android.widget.Button[@text='Buy It Now']")
    private WebElement buyItNowFilter;

    @FindBy(xpath = "//android.widget.Button[@text='Show More']")
    private WebElement showMoreButton;

    @FindBy(xpath = "//android.widget.ToggleButton[@resource-id='switch__LH_ItemCondition']")
    private WebElement eBayRefurbishedFilter;

    @FindBy(xpath = "//android.app.Dialog[@text='Filter']/android.view.View/android.view.View[3]/android.widget.Button")
    private WebElement showResultsButton;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='filter__title filter__title--hub']")
    private WebElement title;

    private final Logger logger = LogManager.getLogger(FiltersPage.class);

    private final WebDriverWait wait;

    public FiltersPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tapBuyItNowFilter() {
        buyItNowFilter.click();
    }

    public void tapShowMore() {
        showMoreButton.click();
    }

    public void tapEbayRefurbished() {
        eBayRefurbishedFilter.click();
    }

    public void tapShowResults() {
        showResultsButton.click();
    }

    public boolean isPageOpened() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(title));
            return title.isDisplayed();
        } catch (Exception e) {
            logger.error("Filter page is not opened: ", e);
            return false;
        }
    }
}
