package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        switch (format) {
            case "csv":
                saveAsCsv(contacts, new File(file));
                break;
            case "xml":
                saveAsXml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts, new File(file));
                break;
            default:
                System.out.println("Unrecognized format " + format);
                break;
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml= xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getLastName(), contact.getFirstname()
                    , contact.getCompany(), contact.getAddress(), contact.getPhoneHome(),contact.getPhoneMobile()
                    ,contact.getPhoneWork(), contact.getEmailOne(), contact.getEmailTwo()));
        }
        writer.close();
    }


    private List<ContactData> generateContacts(int counts) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < counts; i++) {
            String lastname = "Petroff %s";
            String company = "Google %s";
            String firstname = "Petr %s";
            String address = "Moscow-city %s";
            String phoneHome = "123455 %s";
            String phoneMobile = "12345 %s";
            String phoneWork = "122345 %s";
            String emailOne = "12@gmail.com %s";
            String emailTwo = "12@mail.com %s";
            contacts.add(new ContactData(String.format(lastname, i)
                    , String.format(company, i)
                    , String.format(firstname, i)
                    , String.format(address, i)
                    , String.format(phoneHome, i)
                    , String.format(phoneMobile, i)
                    , String.format(phoneWork, i)
                    , String.format(emailOne, i)
                    , String.format(emailTwo, i)));
        }
        return contacts;
    }
}
