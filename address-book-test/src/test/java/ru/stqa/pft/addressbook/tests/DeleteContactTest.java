package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTest extends TestBase {

    @Test(enabled = true)
    public void deleteContactTest() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Pavel"
                    , "Ivanov"
                    , "skynet"
                    , "new-york"
                    , "111111111"));
        }
        List<ContactData> before = app.getContactHelper().getList();
        app.getContactHelper().deleteContact();
        app.getContactHelper().home();
        List<ContactData> after = app.getContactHelper().getList();

        Assert.assertEquals(after.size() + 1, before.size());

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
