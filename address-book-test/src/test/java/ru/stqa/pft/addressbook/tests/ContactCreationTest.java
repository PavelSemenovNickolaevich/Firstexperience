package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testCreateNewContact() throws Exception {
        applicationManager.getNavigationHelper().goToHome();
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        ContactData contact = new ContactData("Pavel111","First", "Ivanov"
                , "skynet", "new-york", "111111111");
     //   int before = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов до
        applicationManager.getNavigationHelper().goToAddNewContact();
        applicationManager.getContactHelper().fillContactInfo(contact);
        applicationManager.getContactHelper().submitContact();
        applicationManager.getContactHelper().returnHomeContact();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
     //   int after = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size() + 1);  //Сверка счетчиков

        int max = 0;
        for (ContactData g: after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
      //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.getContactHelper().logoutContact();
    }

}