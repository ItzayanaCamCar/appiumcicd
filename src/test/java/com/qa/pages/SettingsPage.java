package com.qa.pages;

import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BaseTest {

    TestUtils utils = new TestUtils();

    @AndroidFindBy(accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(id = "test-LOGOUT")
    private WebElement logoutBtn;


    public LoginPage pressLogoutBtn() {
        click(logoutBtn, "Press Logout button");
        return new LoginPage();
    }
}
