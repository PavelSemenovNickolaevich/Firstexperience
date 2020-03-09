package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationUpdateTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditionals() {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet"
                            , "new-york", "111111111", "643634643"));
        }
    }

    @Test
    public void testContactUpdate() {
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Ivan01"
                , "Groznie", "Skynet", "Moscow","777777777", "4w6w6");
     //   int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        applicationManager.getContactHelper().editContact(before.size() - 1 );
        applicationManager.getContactHelper().fillContactInfo(contact);
        applicationManager.getContactHelper().updateContact();
        applicationManager.getContactHelper().returnHomeContact();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
     //   int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size());  //Сверка контактов
        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        applicationManager.getContactHelper().logoutContact();
    }
}


/*before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);*/