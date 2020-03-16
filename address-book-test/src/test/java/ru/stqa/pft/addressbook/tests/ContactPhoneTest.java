package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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
                            , "new-york", "111111111", "+3535", "123(0)", "333"));
        }
    }

    @Test
    public void testContactPhones() {
    //    applicationManager.goTo().goToAddNewContact();
        ContactData contact = applicationManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = applicationManager.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getPhoneHome(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneHome())));
        MatcherAssert.assertThat(contact.getPhoneMobile(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneMobile())));
        MatcherAssert.assertThat(contact.getPhoneWork(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneWork())));

    }

    public  String cleaned(String phone) {
        //очищение строчек
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}