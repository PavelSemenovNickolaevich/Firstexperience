package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test(enabled = true)
    public void testCreateNewContact() {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/terminator.png");
        ContactData contact = new ContactData("Pavel"
                , "Ivan7ov"
                , "skynet"
                , "new-york"
                , "111", "222", "333"
                , "123@gmail.com", "333-@13.com")
                .withPhoto(photo);
        app.contact().createContact(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.setId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        //     Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        // before.add(contact);
//        before.sort(byId);
//        after.sort(byId);
        //Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
        app.contact().logoutContact();
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/terminator.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }


}