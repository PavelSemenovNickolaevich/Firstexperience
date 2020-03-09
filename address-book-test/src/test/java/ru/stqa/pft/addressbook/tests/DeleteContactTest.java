package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public  void ensurePreconditionals() {
        applicationManager.goTo().goToHome();
        if (applicationManager.contact().getContactList().size() == 0) {
            applicationManager.contact()
                    .createContact(new ContactData("Pavel", "Ivanov", "skynet", "new-york", "111111111", "454545454"));
        }
    }

    @Test
    public void deleteContactTest() {
        List<ContactData> before = applicationManager.contact().getContactList();
      //  int before = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов до
        int index = before.size() - 1;
        applicationManager.contact().deleteContact(index);
        List<ContactData> after = applicationManager.contact().getContactList();
      //  int after = applicationManager.getContactHelper().getContactCount();  //Счетчик контактов после
        Assert.assertEquals(after.size(), before.size() -1 ); // Сверка счетчиков

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
