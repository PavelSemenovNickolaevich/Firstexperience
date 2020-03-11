package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationUpdateTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditionals () {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().all().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet"
                            , "new-york", "111111111", "643634643"));
        }
    }

    @Test
    public void testContactUpdate () {
        Set<ContactData> before = applicationManager.contact().all();
        ContactData modifyContact = before.iterator().next();

        //List<ContactData> before = applicationManager.contact().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(modifyContact.getId(), "Ivan01"
                , "Groznie", "Skynet", "Moscow", "777777777", "4w6w6");
        //   int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        applicationManager.contact().modifyContact(index, contact);
        //    List<ContactData> after = applicationManager.contact().getContactList();
        Set<ContactData> after = applicationManager.contact().all();
        //   int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size());  //Сверка контактов
        before.remove(modifyContact);
        before.add(contact);
        //  Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.contact().logoutContact();
    }

}


/*before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/