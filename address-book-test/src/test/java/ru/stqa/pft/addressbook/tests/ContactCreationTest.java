package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test(enabled = true)
    public void testCreateNewContact() throws Exception {
        List<ContactData> before = app.getContactHelper().getList();
        app.goTo().goToContact();
        ContactData contact =new ContactData("Pavel"
                , "Ivanov"
                , "skynet"
                , "new-york"
                , "111111111");
        app.getContactHelper().fillContactInfo(contact);
        app.getContactHelper().submitContact();
        app.getContactHelper().returnHomeContact();
        List<ContactData> after = app.getContactHelper().getList();

        Assert.assertEquals(after.size(), before.size() + 1);

        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.add(contact);
        before.sort(byId);
        after.sort(byId);
        //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        Assert.assertEquals(before, after);

        app.getContactHelper().logoutContact();

    }

}