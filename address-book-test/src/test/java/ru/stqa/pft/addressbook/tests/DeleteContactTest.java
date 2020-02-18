package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void deleteContactTest() {
        applicationManager.getContactHelper().home();
        applicationManager.getContactHelper().deleteContact();
        applicationManager.getContactHelper().home();

    }

}
