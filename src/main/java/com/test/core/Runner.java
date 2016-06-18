package com.test.core;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


/**
 * Created by cigniti_apasunoori on 6/16/16.
 */
public class Runner {


    WebElement element;
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    RemoteWebDriver remoteWebDriver;
    AndroidDriver driver;

    static Logger log = Logger.getLogger(Runner.class.getName());
    DesiredCapabilities capabilities = new DesiredCapabilities();
    Properties prop = new Properties();
    InputStream input = null;
    String value;
    WebDriverWait wait;


    public String getValue(String key) {

        try {
            String path = "/src/main/resources/";
            String completePath = System.getProperty("user.dir") + path;
            //   log.info(completePath);

            input = new FileInputStream(completePath + "config.properties");
            prop.load(input);

            value = prop.getProperty(key);

        } catch (IOException e) {
            System.err.println("looks like no config file Caught IOException: " + e.getMessage());
        }

        return value;
    }

    public AndroidDriver createDriver(String android) {
        if (android.equalsIgnoreCase("AndroidWeb")) {

            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, getValue("mobileBrowser"));
            desiredCapabilities.setCapability("platformName", getValue("platformName"));
            desiredCapabilities.setCapability("platformVersion", getValue("platformVersion"));
            desiredCapabilities.setCapability("device", getValue("device"));
            desiredCapabilities.setCapability("deviceName", getValue("deviceName"));

            URL url = null;
            try {
                url = new URL("http://127.0.0.1:4725/wd/hub");
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            remoteWebDriver = new RemoteWebDriver(url, desiredCapabilities);
        }
        if (android.equalsIgnoreCase("AndroidNative")) {

            //  desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, getValue("mobileBrowser"));
            desiredCapabilities.setCapability("platformName", getValue("platformName"));
            desiredCapabilities.setCapability("platformVersion", getValue("platformVersion"));
            desiredCapabilities.setCapability("device", getValue("device"));
            desiredCapabilities.setCapability("deviceName", getValue("deviceName"));
            desiredCapabilities.setCapability("appActivity", getValue("appActivity"));
            desiredCapabilities.setCapability("appPackage", getValue("appPackage"));

            URL url = null;
            try {
                url = new URL("http://127.0.0.1:4725/wd/hub");
            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            driver = new AndroidDriver(url, desiredCapabilities);


        }
        return driver;
    }

    public WebElement findElement(int locatorType, String locator)  {

        try {
            switch (locatorType) {
                case 1:

                    element = driver.findElement(By.id(locator));
                    break;
                case 2:
                    wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
                    element = driver.findElement(By.name(locator));
                    break;
                case 3:
                    System.out.print("----------");
                    System.out.println("locatortype=>"+locatorType);
                    System.out.println("locator =>"+locator);
                    System.out.print("----------");
//                     wait = new WebDriverWait(driver, 10);
//                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                    element = driver.findElement(By.xpath(locator));
                    break;
                case 4:
                    element = driver.findElement(By.cssSelector(locator));
                    break;


            }

        } catch(Exception e) {
         System.out.print(e);


        }
        return element;
    }

    public void simpleWait() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void closeDriver() {
        driver.close();
    }

    public void quitDriver() {
        driver.quit();
    }
}


