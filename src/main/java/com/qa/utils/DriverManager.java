package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    TestUtils utils = new TestUtils();

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParams globalParams = new GlobalParams();

        if (driver == null) {
            try {
                utils.log().info("Initializing appium driver.");

                switch (globalParams.getPlatformName()) {
                    case "Android":
                        driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCapabilities());
                        break;

                    case "iOS":
                        driver = new IOSDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCapabilities());
                        break;
                }
                if (driver == null) {
                    throw new Exception("Driver is null. ");
                }
                utils.log().info("Driver is initialized. ");
                DriverManager.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ");
                throw e;
            }

        }
    }
}
