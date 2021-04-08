package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailAddressTests extends TestBase {

    @Test
    public void testContactPhonesEmailsAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePnones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "")
                .replaceAll(" ", "");
    }

    private String mergePnones(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmailOne(), contact.getEmailTwo())
                .stream().filter(s -> !s.equals(""))
                .map(ContactPhoneEmailAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddress(ContactData contact) {
        return Collections.singletonList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                //   .map(ContactPhoneAddressEmailTest::cleaned)
                .collect(Collectors.joining("\n"));

    }
}
