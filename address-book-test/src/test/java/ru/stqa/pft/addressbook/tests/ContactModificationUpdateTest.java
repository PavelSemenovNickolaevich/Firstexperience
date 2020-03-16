package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

public class ContactModificationUpdateTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditionals () {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().all().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel111", "First", "Ivanov"
                            , "skynet", "Moscow 3-builder street 10", "111", "222",
                            "333", "123@gmail.com", "ivanov@mail.com" ));
        }
    }

    @Test
    public void testContactUpdate () {
        Contacts before = applicationManager.contact().all();
        ContactData modifyContact = before.iterator().next();

        //List<ContactData> before = applicationManager.contact().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(modifyContact.getId(), "Pavel111", "First", "Ivanov"
                , "skynet", "Moscow 3-builder street 10", "111", "222",
                "333", "123@gmail.com", "ivanov@mail.com");
        //   int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        applicationManager.contact().modifyContact(index, contact);
        //    List<ContactData> after = applicationManager.contact().getContactList();
        Set<ContactData> after = applicationManager.contact().all();
        //   int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size());  //Сверка контактов
        before.remove(modifyContact);
        before.add(contact);
        Assert.assertEquals(before, after);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo((before.without(modifyContact).withAdded(contact))));
        //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.contact().logoutContact();
    }

}


/*before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/