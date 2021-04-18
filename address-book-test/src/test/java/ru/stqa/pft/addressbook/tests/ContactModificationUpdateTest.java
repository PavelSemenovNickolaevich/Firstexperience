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

public class ContactModificationUpdateTest extends TestBase {
    private Properties properties;

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/local.properties")));
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
    public void testContactUpdate() throws InterruptedException, IOException {
        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/local.properties")));
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = (new ContactData(modifyContact.getId(), properties.getProperty("web.firstname")
                , properties.getProperty("web.lastName")
                , properties.getProperty("web.company")
                , properties.getProperty("web.address")
                , properties.getProperty("web.phoneHome")
                , properties.getProperty("web.phoneMobile")
                , properties.getProperty("web.phoneWork")
                , properties.getProperty("web.emailOne")
                , properties.getProperty("web.emailTwo")));
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifyContact);
        before.add(contact);
//        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//        after.sort(byId);
//        before.sort(byId);
        Assert.assertEquals(after, before);
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }
}
