package ru.stqa.pft.addressbook.tests;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanger.ApplicationManager;

public class TestBase {

    protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

}
