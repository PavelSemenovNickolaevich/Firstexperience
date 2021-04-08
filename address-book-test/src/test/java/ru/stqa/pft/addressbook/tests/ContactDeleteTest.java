package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // app.goTo().groupPage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData("Pave66l"
                    , "Ivanov"
                    , "skynet"
                    , "new-york"
                    , "111", "222", "333"
                    , "123@gmail.com", "333-@13.com"));
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
