package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanger.ApplicationManager;

import java.lang.reflect.Method;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager applicationManager
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeClass()
    public void setUp () throws Exception {
        applicationManager.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown () throws Exception {
        applicationManager.stop();
    }
    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test"+ m.getName() );
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }

}
