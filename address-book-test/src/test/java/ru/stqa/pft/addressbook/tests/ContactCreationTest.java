package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validTestFromJson() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            StringBuilder json = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                json.append(line);
                line = bufferedReader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>() {  //List<ContactData>.class
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validTestFromXml() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
            StringBuilder xml = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                xml.append(line);
                line = bufferedReader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(String.valueOf(xml));
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validTestFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new ContactData(split[0], split[1], split[2]
                        , split[3], split[4], split[5], split[6]
                        , split[7], split[8])});
                line = bufferedReader.readLine();
            }
            return list.iterator();
        }
    }

    @Test(dataProvider = "validTestFromCsv")
    public void testCreateNewContact(ContactData contact) {
        Contacts before = app.contact().all();
        app.goTo().goToAddNewContact();
//        File photo = new File("src/test/resources/terminator.png");
//        ContactData contact = new ContactData("Pavel"
//                , "Ivan7ov"
//                , "skynet"
//                , "new-york"
//                , "111", "222", "333"
//                , "123@gmail.com", "333-@13.com")
//                .withPhoto(photo);
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
        // app.contact().logoutContact();
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