/**
 * Created by cigniti_apasunoori on 6/16/16.
 */

import com.test.core.Runner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {

    @Test
    public void MobileWeb() {
        String driverValue;
        Runner runner = new Runner();
        driverValue = runner.getValue("deviceName");
        runner.createDriver(driverValue);

        System.out.print(driverValue + " logs only");

        String page_url = "https://www.google.com";



        remoteWebDriver.get(page_url);
        String pageTitle = remoteWebDriver.getTitle();

        Assert.assertEquals("Google", pageTitle);

        remoteWebDriver.quit();

    }


}
