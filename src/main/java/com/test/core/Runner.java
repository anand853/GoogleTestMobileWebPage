package com.test.core;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * Created by cigniti_apasunoori on 6/16/16.
 */
public class Runner {

    static Logger log = Logger.getLogger(Runner.class.getName());
    DesiredCapabilities capabilities = new DesiredCapabilities();
    Properties prop = new Properties();
    InputStream input = null;
    String value;

    public String getValue(String key) {

        try {
            String path = "/src/main/resources/";
            String completePath = System.getProperty("user.dir") + path;
            log.info(completePath);

            input = new FileInputStream(completePath + "config.properties");
            prop.load(input);

            value = prop.getProperty(key);

        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return value;
    }

    public void createDriver(String android) {
        if (android.equalsIgnoreCase("AndroidWeb")) {

        }
        if (android.equalsIgnoreCase("Android")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, getValue("mobileBrowser"));
            desiredCapabilities.setCapability("platformName", getValue("platformName"));
            desiredCapabilities.setCapability("platformVersion", getValue("platformVersion"));
            desiredCapabilities.setCapability("device", getValue("device"));
            desiredCapabilities.setCapability("deviceName", getValue("deviceName"));

            URL url = null;
            try {
                url = new URL("http://localhost:4723/wd/hub");
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
        }
    }
}
