package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeleteContactTest extends TestBase {

    @Test
    public void deleteContactTest() {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper().createContact(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111"));
        }
        int before = applicationManager.getContactHelper().getContactCount(); //Счетчик контактов до
        applicationManager.getContactHelper().deleteContact();
        applicationManager.getContactHelper().home();
        int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after, before -1 ); // Сверка счетчиков
    }
}
