package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;

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

        assertThat(contact.getPhoneHome(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneHome())));
        assertThat(contact.getPhoneMobile(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneMobile())));
        assertThat(contact.getPhoneWork(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getPhoneWork())));
        assertThat(contact.getEmailOne(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailOne())));
        assertThat(contact.getEmailTwo(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailTwo())));
      //  assertThat(contact.getAdress(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getAdress())));
        assertThat(contact.getAdress(), CoreMatchers.equalTo(mergeAdress(contactInfoFromEditForm)));

    }

    private String mergeAdress (ContactData contact) {
        return Arrays.asList(contact.getAdress())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining(""));

    }

    public  String cleaned(String phone) {
        //очищение строчек
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}