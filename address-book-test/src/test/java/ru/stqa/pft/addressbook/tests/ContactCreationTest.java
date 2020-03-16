package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

    @Test
    public void testCreateNewContact () throws Exception {
        applicationManager.goTo().goToHome();
        Contacts before = applicationManager.contact().all();
        applicationManager.goTo().goToAddNewContact();
        // List<ContactData> before = applicationManager.contact().getContactList();
        //  Contacts before = applicationManager.contact().getContactList();
        // Set<ContactData> before = applicationManager.contact().all();
        //   Contacts before = applicationManager.contact().all();
        ContactData contact = new ContactData("Pavel111", "First", "Ivanov"
                , "skynet", "Moscow 3-builder street 10", "111", "222",
                "333", "123@gmail.com", "ivanov@mail.com"  );
        //   int before = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов до
        applicationManager.contact().createNew(contact);
        // List<ContactData> after = applicationManager.contact().getContactList();
        //  Contacts after = applicationManager.contact().getContactList();
        //   Set<ContactData> after = applicationManager.contact().all();
        Contacts after = applicationManager.contact().all();
        //   int after = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов после
        //  Assert.assertEquals(after.size(), before.size() + 1);//Сверка счетчиков
        assertThat(after.size(), equalTo(before.size() + 1));

    /*    int max = 0;
        for (ContactData g: after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }*/
        //   contact.setId(max);
        contact.setId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        //    before.add(contact);
        //    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //   before.sort(byId);
        //    after.sort(byId);
        // Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(contact)));
        //    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.contact().logoutContact();
    }


}