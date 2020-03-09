package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public  void ensurePreconditionals() {
        applicationManager.getNavigationHelper().goToHome();
        if (!applicationManager.getContactHelper().isThereAContact()) {
            applicationManager.getContactHelper()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111", "454545454"));
        }
    }

    @Test
    public void deleteContactTest() {
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
      //  int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        applicationManager.getContactHelper().selectContact(before.size() - 1);
        applicationManager.getContactHelper().deleteContact();
        applicationManager.getContactHelper().home();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
      //  int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size() -1 ); // Сверка счетчиков

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
