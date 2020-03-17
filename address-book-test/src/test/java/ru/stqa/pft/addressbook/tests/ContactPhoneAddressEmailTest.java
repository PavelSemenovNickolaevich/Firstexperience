package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactPhoneAddressEmailTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditionals() {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().all().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel111", "First", "Ivanov"
                            , "skynet", "Moscow 3-builder street 10", "111", "222",
                            "333", "123@gmail.com", "ivanov@mail.com" ));
        }
    }

    @Test
    public void testContactPhonesEmailAdress() {
    //    applicationManager.goTo().goToAddNewContact();
        ContactData contact = applicationManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = applicationManager.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getPhoneHome(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneHome())));
        MatcherAssert.assertThat(contact.getPhoneMobile(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneMobile())));
        MatcherAssert.assertThat(contact.getPhoneWork(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneWork())));
        MatcherAssert.assertThat(contact.getEmailOne(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailOne())));
        MatcherAssert.assertThat(contact.getEmailTwo(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailTwo())));
        MatcherAssert.assertThat(contact.getAdress(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getAdress())));

    }

    public  String cleaned(String phone) {
        //очищение строчек
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}