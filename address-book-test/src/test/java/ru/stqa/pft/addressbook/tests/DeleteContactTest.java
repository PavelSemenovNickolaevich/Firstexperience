package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeleteContactTest extends TestBase {

    @Test
    public void deleteContactTest() {
        applicationManager.getNavigationHelper().goToContact();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper().createContact(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111"));
        }
        applicationManager.getContactHelper().deleteContact();
        applicationManager.getContactHelper().home();
    }
}
