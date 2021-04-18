package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase {
    private Properties properties;

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        // app.goTo().groupPage();
        properties = new Properties();
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData(properties.getProperty("web.firstname")
                    , properties.getProperty("web.lastName")
                    , properties.getProperty("web.company")
                    , properties.getProperty("web.address")
                    , properties.getProperty("web.phoneHome")
                    , properties.getProperty("web.phoneMobile")
                    , properties.getProperty("web.phoneWork")
                    , properties.getProperty("web.emailOne")
                    , properties.getProperty("web.emailTwo")));
        }
    }

    @Test(enabled = true)
    public void deleteContactTest() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact();
        app.contact().home();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
