package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationUpdateTest extends TestBase{

    @Test
    public void testContactUpdate() {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper().createContact(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111"));
        }
        applicationManager.getContactHelper().editContact();
        applicationManager.getContactHelper().fillContactInfo(new ContactData("Ivan", "Groznie", "Skynet", "Moscow","777777777"));
        applicationManager.getContactHelper().updateContact();
        applicationManager.getContactHelper().returnHomeContact();
        applicationManager.getContactHelper().logoutContact();
    }
}
