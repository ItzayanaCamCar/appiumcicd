package com.qa.runners;

import com.qa.utils.DriverManager;
import com.qa.utils.GlobalParams;
import com.qa.utils.ServerManager;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.ThreadContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber.html",
                "summary",
        },
        features = {"src/test/resources"}
        , glue = {"com.qa.stepdef"}
        , snippets = CucumberOptions.SnippetType.CAMELCASE
        , dryRun = false
        , monochrome = true
        , tags = "@test"
)
public class MyRunnerTest {

    @BeforeClass
    public static void initialize() throws Exception {
        GlobalParams globalParams = new GlobalParams();
        globalParams.initializeGlobalParams();
        ThreadContext.put("ROUTINGKEY", globalParams.getPlatformName() + "_" + globalParams.getDeviceName());
        new ServerManager().startServer();
        new DriverManager().initializeDriver();
    }

    @AfterClass
    public static void quit() {
        DriverManager driverManager = new DriverManager();
        if (driverManager.getDriver() != null) {
            driverManager.getDriver().quit();
            driverManager.setDriver(null);
        }

        ServerManager serverManager = new ServerManager();
        if (serverManager.getServer() != null) {
            serverManager.getServer().stop();
        }
    }
}
