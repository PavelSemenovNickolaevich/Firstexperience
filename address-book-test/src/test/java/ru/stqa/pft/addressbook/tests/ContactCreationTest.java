package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testCreateNewContact() throws Exception {
        applicationManager.getNavigationHelper().goToHome();
        int before = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов до
        applicationManager.getNavigationHelper().goToAddNewContact();
        applicationManager.getContactHelper().fillContactInfo(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111"));
        applicationManager.getContactHelper().submitContact();
        applicationManager.getContactHelper().returnHomeContact();
        int after = applicationManager.getContactHelper().getContactCount();   //Счетчик контактов после
        Assert.assertEquals(after, before + 1);  //Сверка счетчиков
        applicationManager.getContactHelper().logoutContact();
    }

}