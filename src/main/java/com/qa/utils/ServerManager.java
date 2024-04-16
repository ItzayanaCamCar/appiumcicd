package com.qa.utils;

import com.google.common.io.ByteStreams;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

public class ServerManager {

    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    public AppiumDriverLocalService getServer() {
        return server.get();
    }

    public void startServer() {
        utils.log().info("Starting appium server...");
        AppiumDriverLocalService server = MacGetAppiumService();
        server.start();

        if (server == null || server.isRunning()) {
            utils.log().fatal("Appium server not started. ");
        }
        ServerManager.server.set(server);
        utils.log().info("Appium server started");
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    public AppiumDriverLocalService WindowsGetAppiumService() {
        GlobalParams globalParams = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withLogFile(new File(globalParams.getPlatformName() + "_"
                        + globalParams.getDeviceName() + File.separator + "Server.log")));

    }

    public AppiumDriverLocalService MacGetAppiumService() {
        GlobalParams globalParams = new GlobalParams();
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/Users/itzayana_cam_car/Library/Android/sdk/platform-tools:/Users/itzayana_cam_car/Library/Android/sdk/cmdline-tools:/Library/Java/JavaVirtualMachines/jdk-15.0.2.jdk/Contents/Home/bin:/usr/local/opt/node@14/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/itzayana_cam_car/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogOutput(ByteStreams.nullOutputStream())
                .withLogFile(new File(globalParams.getPlatformName() + "_"
                        + globalParams.getDeviceName() + File.separator + "Server.log")));

    }
    
}
