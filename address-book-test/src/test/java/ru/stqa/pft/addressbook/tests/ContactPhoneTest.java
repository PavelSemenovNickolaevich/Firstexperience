package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditionals() {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().all().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet"
                            , "new-york", "111111111", "111", "222", "333"));
        }
    }

    @Test
    public void testContactPhones() {
        applicationManager.goTo().goToAddNewContact();
        ContactData contact = applicationManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = applicationManager.contact().infoFromEditForm(contact);


    }
}