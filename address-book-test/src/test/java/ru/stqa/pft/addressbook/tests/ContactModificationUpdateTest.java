package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationUpdateTest extends TestBase{

    @Test(enabled = true)
    public void testContactUpdate() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Pavel"
                    , "Ivanov"
                    , "skynet"
                    , "new-york"
                    , "111111111"));
        }
        List<ContactData> before = app.getContactHelper().getList();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactInfo(new ContactData("Ivan"
                , "Groznie"
                , "Skynet"
                , "Moscow"
                ,"777777777"));
        app.getContactHelper().updateContact();
        app.getContactHelper().returnHomeContact();
        List<ContactData> after = app.getContactHelper().getList();
        app.getContactHelper().logoutContact();

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(after, before);



    }
}
