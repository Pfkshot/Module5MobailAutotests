import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
    private AndroidDriver driver;

    public AndroidDriver<?> setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");

//        desiredCapabilities.setCapability(APP,"/Users/PC/Desktop/Test/AutotestMobile/lesson4/Zoom.apk");



        URL remoteUrl = new URL("http://localhost:4723/wd/hub");


        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }
}
