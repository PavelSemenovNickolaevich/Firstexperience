package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationUpdateTest extends TestBase{

    @Test
    public void testContactUpdate() {
        //applicationManager.getContactHelper().returnHomeContact();
        applicationManager.getContactHelper().editContact();
    }
}
