package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testCreateNewContact() throws Exception {
        applicationManager.getNavigationHelper().goToHome();
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
     //   int before = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов до
        applicationManager.getNavigationHelper().goToAddNewContact();
        applicationManager.getContactHelper().fillContactInfo(new ContactData("Pavel","Ivanov", "Ivanov", "skynet", "new-york", "111111111"));
        applicationManager.getContactHelper().submitContact();
        applicationManager.getContactHelper().returnHomeContact();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
     //   int after = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size() + 1);  //Сверка счетчиков
        applicationManager.getContactHelper().logoutContact();
    }

}