package com.qa.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {

    TestUtils utils = new TestUtils();

    public DesiredCapabilities getCapabilities() throws IOException {

        GlobalParams globalParams = new GlobalParams();
        Properties properties = new PropertyManager().getProperties();

        try {
            utils.log().info("Getting capabilities");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", properties.getProperty("platformName"));
            capabilities.setCapability("udid", globalParams.getUdid());
            capabilities.setCapability("deviceName", properties.getProperty("deviceName"));

            switch (globalParams.getPlatformName()) {

                case "Android":
                    String appPathAnd = System.getProperty("user.dir") + properties.getProperty("androidAppLocation");
                    utils.log().info("Path app android is " + appPathAnd);

                    capabilities.setCapability("automationName", properties.getProperty("androidAutomationName"));
                    capabilities.setCapability("appPackage", properties.getProperty("androidAppPackage"));
                    capabilities.setCapability("appActivity", properties.getProperty("androidAppActivity"));
                    capabilities.setCapability("systemPort", globalParams.getSystemPort());
                    capabilities.setCapability("chromeDriverPort", globalParams.getChromeDriverPort());
                    capabilities.setCapability("app", appPathAnd);
                    break;

                case "iOS":
                    String appUrlIOS = System.getProperty("user.dir") + properties.getProperty("iOSAppLocation");
                    utils.log().info("Path app iOS is " + appUrlIOS);

                    capabilities.setCapability("automationName", properties.getProperty("iOSAutomationName"));
                    capabilities.setCapability("bundleId", properties.getProperty("iOSBundleId"));
                    capabilities.setCapability("wdaLocalPort", globalParams.getWdaLocalPort());
                    capabilities.setCapability("webkitDebugProxyPort", globalParams.getWebkitDebugProxyPort());
                    capabilities.setCapability("app", appUrlIOS);
                    break;
            }
            return capabilities;

        } catch (Exception e) {
            e.printStackTrace();
            utils.log().fatal("Failed to load capabilities. " + e.toString());
            throw e;
        }
    }
}
