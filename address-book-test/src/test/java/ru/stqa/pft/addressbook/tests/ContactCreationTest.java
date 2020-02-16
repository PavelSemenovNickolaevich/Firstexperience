package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testCreateNewContact() throws Exception {
        applicationManager.getNavigationHelper().goToContact();
        applicationManager.getContactHelper().fillContactInfo(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111"));
        applicationManager.getContactHelper().submitContact();
        applicationManager.getContactHelper().returnHomeContact();
        applicationManager.getContactHelper().logoutContact();
    }

}