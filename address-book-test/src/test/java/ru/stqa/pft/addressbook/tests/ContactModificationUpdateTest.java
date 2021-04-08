package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationUpdateTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData("Pave66l"
                    , "Ivanov"
                    , "skynet"
                    , "new-york Test Test"
                    , "+(916)511-343", "2 2 2", "3-3-3"
                    , "123@gmail.com", "333-@13.com"
            ));
        }
    }

    @Test(enabled = true)
    public void testContactUpdate() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData(modifyContact.getId(), "Ivan"
                , "Groznie"
                , "Skynet"
                , "Moscow Test Test"
                , "+(916)511-343", "2 2 2", "3-3-3"
                , "123@gmail.com", "333-@13.com");
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
