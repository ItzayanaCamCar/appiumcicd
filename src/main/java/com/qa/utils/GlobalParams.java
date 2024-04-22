package com.qa.utils;

public class GlobalParams {
    private static final ThreadLocal<String> platformName = new ThreadLocal<String>();
    private static final ThreadLocal<String> udid = new ThreadLocal<String>();
    private static final ThreadLocal<String> deviceName = new ThreadLocal<String>();
    private static final ThreadLocal<String> systemPort = new ThreadLocal<String>();
    private static final ThreadLocal<String> chromeDriverPort = new ThreadLocal<String>();
    private static final ThreadLocal<String> wdaLocalPort = new ThreadLocal<String>();
    private static final ThreadLocal<String> webkitDebugProxyPort = new ThreadLocal<String>();

    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName) {
        GlobalParams.platformName.set(platformName);
    }

    public String getUdid() {
        return udid.get();
    }

    public void setUdid(String udid) {
        GlobalParams.udid.set(udid);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName) {
        GlobalParams.deviceName.set(deviceName);
    }

    public String getSystemPort() {
        return systemPort.get();
    }

    public void setSystemPort(String systemPort) {
        GlobalParams.systemPort.set(systemPort);
    }

    public String getChromeDriverPort() {
        return chromeDriverPort.get();
    }

    public void setChromeDriverPort(String chromeDriverPort) {
        GlobalParams.chromeDriverPort.set(chromeDriverPort);
    }

    public String getWdaLocalPort() {
        return wdaLocalPort.get();
    }

    public void setWdaLocalPort(String wdaLocalPort) {
        GlobalParams.wdaLocalPort.set(wdaLocalPort);
    }

    public String getWebkitDebugProxyPort() {
        return webkitDebugProxyPort.get();
    }

    public void setWebkitDebugProxyPort(String webkitDebugProxyPort) {
        GlobalParams.webkitDebugProxyPort.set(webkitDebugProxyPort);
    }

    public void initializeGlobalParams() {
        GlobalParams globalParams = new GlobalParams();

        // globalParams.setPlatformName(System.getProperty("platformName", "Android"));
        // globalParams.setUdid(System.getProperty("udid", "9301a54d"));
        // globalParams.setDeviceName(System.getProperty("deviceName", "Xiaomi_Note_10"));

        switch (globalParams.getPlatformName()) {

            case "Android":
                globalParams.setSystemPort(System.getProperty("systemPort", "10000"));
                globalParams.setChromeDriverPort(System.getProperty("chromeDriverPort", "11000"));
                break;

            case "iOS":
                globalParams.setWdaLocalPort(System.getProperty("wdaLocalPort", "10001"));
                globalParams.setWebkitDebugProxyPort(System.getProperty("webkitDebugProxyPort", "11001"));
                break;

            default:
                throw new IllegalStateException("Inválid platform");
        }
    }
}
