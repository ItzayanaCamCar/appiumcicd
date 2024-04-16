package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {

    @When("I enter  username as {string}")
    public void iEnterUsernameAs(String username) {
        new LoginPage().enterUserName(username);
    }

    @And("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        new LoginPage().enterPassword(password);
    }

    @And("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
    }

    @Then("Login should fail with an error {string}")
    public void loginShouldFailWithAnError(String err) {
        Assert.assertEquals(new LoginPage().getErrTxt(), err);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assert.assertEquals(new ProductsPage().getTitle(), title);
    }
}
