import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.junit.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class M5  {

    @AndroidFindBy(id = "us.zoom.videomeetings:id/btnJoinConf")
    MobileElement btnJoinConf;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/join_conf_btn_back")
    MobileElement btnBack;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/join_conf_title_view")
    MobileElement titleJoin;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Meeting']")
    MobileElement btnMeeting;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout/ancestor::android.widget.LinearLayout[1]")
    MobileElement inputFieldMeetingID;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/edtConfNumber")
    MobileElement meetingID;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/btnGotoVanityUrl")
    MobileElement personalLink;

    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Test
    public void sampleTests() throws InterruptedException {

        Assert.assertTrue(btnJoinConf.isDisplayed());
        Assert.assertTrue(btnJoinConf.isEnabled());
        Assert.assertFalse(btnJoinConf.isSelected());

        swipe(Direction.LEFT);
        swipe(Direction.RIGHT);

        Assert.assertFalse(rotateToLandscape());


        btnJoinConf.click();

        wait2sec(MobileBy.id("us.zoom.videomeetings:id/join_conf_title_view"));

        driver.rotate(ScreenOrientation.PORTRAIT);

        btnBack.isDisplayed();

        titleJoin.isDisplayed();

        btnMeeting.isDisplayed();

        inputFieldMeetingID.isDisplayed();

        meetingID.isDisplayed();

        personalLink.isDisplayed();

        Assert.assertEquals("Join",titleJoin.getText());

        MobileElement googleSDK = (MobileElement) driver.findElementById("us.zoom.videomeetings:id/edtScreenName");

        MobileElement txtMsgTermsAndPrivacy = (MobileElement) driver.findElementById("us.zoom.videomeetings:id/txtMsgTermsAndPrivacy");

        MobileElement btnJoin = (MobileElement) driver.findElementById("us.zoom.videomeetings:id/btnJoin");

        MobileElement txtUnderBtnJoin = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("textContains(\"tap on the link to join the meeting\")"));

        MobileElement txtJoinOptions = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("textContains(\"Join Options\")"));

        MobileElement audioOption = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("textContains(\"Don't Connect To Audio\")"));

        MobileElement chkNoAudio = (MobileElement) driver.findElementById("us.zoom.videomeetings:id/chkNoAudio");

        MobileElement videoOption = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("textContains(\"Turn Off My Video\")"));

        MobileElement chkNoVideo = (MobileElement) driver.findElementById("us.zoom.videomeetings:id/chkNoVideo");

        googleSDK.isDisplayed();
        txtMsgTermsAndPrivacy.isDisplayed();
        btnJoin.isDisplayed();
        txtUnderBtnJoin.isDisplayed();
        txtJoinOptions.isDisplayed();
        audioOption.isDisplayed();
        videoOption.isDisplayed();
        chkNoAudio.isDisplayed();
        chkNoVideo.isDisplayed();

        Assert.assertFalse(btnJoin.isEnabled());

        String text = googleSDK.getText();
        googleSDK.clear();
        Assert.assertNotEquals(text, googleSDK.getText());
        googleSDK.sendKeys(text);
        Assert.assertEquals(text,googleSDK.getText());

    }

    private void wait2sec(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/join_conf_title_view")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private enum Direction {
        RIGHT,
        LEFT
    }

    private void swipe(Direction direction){
        int edge = 5;
        int press = 300;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(dims.width/2, dims.height/2);
        PointOption<?> pointOptionEnd;
        switch (direction){
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - edge,dims.height/2);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edge,dims.height/2);
                break;
            default:
                throw new IllegalArgumentException("swipe is not supported");
        }

        new TouchAction<>(driver)
                .press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(press)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();
    }

    private  boolean rotateToLandscape(){
        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            return true;
        }catch (Exception e){
             return false;
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
