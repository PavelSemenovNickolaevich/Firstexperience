package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public  void ensurePreconditionals() {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().all().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet"
                            , "new-york", "111111111", "454545454"));
        }
    }

    @Test
    public void deleteContactTest() {
       // List<ContactData> before = applicationManager.contact().getContactList();
        Contacts before = applicationManager.contact().all();
        ContactData deletedContact = before.iterator().next();
      //  int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        int index = before.size() - 1;
     //   applicationManager.contact().deleteContact(index);
        applicationManager.contact().deleteContact(deletedContact);
      //  List<ContactData> after = applicationManager.contact().getContactList();
        Set<ContactData> after = applicationManager.contact().all();
      //  int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size() -1 ); // Сверка счетчиков

      //  before.remove(index);
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
