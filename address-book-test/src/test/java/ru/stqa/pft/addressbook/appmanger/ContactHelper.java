package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void logoutContact() {
        clickContact(By.linkText("Logout"));
    }

//    public void modifyContact(ContactData contact, int index) {
//        editContact(index);
//        fillContactInfo(contact);
//        updateContact();
//        returnHomeContact();
//    }

    public void modify(ContactData contact) throws InterruptedException {
        clickModifyContactById(contact.getId());
        //   editContact();
        fillContactInfo(contact);
        updateContact();
        returnHomeContact();
    }

    public void fillContactInfo(ContactData contactData) {
        typeContact(By.name("firstname"), contactData.getFirstname());
        typeContact(By.name("lastname"), contactData.getLastName());
        wd.findElement(By.name("company")).click();
        typeContact(By.name("company"), contactData.getCompany());
        typeContact(By.name("address"), contactData.getAddress());
        typeContact(By.name("home"), contactData.getPhoneHome());
        typeContact(By.name("mobile"), contactData.getPhoneMobile());
        typeContact(By.name("work"), contactData.getPhoneWork());
//        typeContact(By.name("email"), contactData.getEmailOne());
//        typeContact(By.name("email2"), contactData.getEmailTwo());
    }

    public void returnHomeContact() {
        clickContact(By.linkText("home page"));
    }

    public void home2() {
        clickContact(By.linkText("home"));
    }

    public void submitContact() {
        clickContact(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void editContact() {
        clickContact(By.linkText("home"));
        clickContact(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContact() {
        // clickContact(By.xpath("//input[@value='Update'][2]"));
        clickContact(By.name("update"));
    }

    public void deleteContact() {
        clickContact(By.name("selected[]"));
        clickContact(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void home() {
        clickContact(By.linkText("home"));
    }

    public void createContact(ContactData contact) {
        addNewContact();
        fillContactInfo(contact);
        submitContact();
        returnHomeContact();
    }

    private void addNewContact() {
        clickContact(By.linkText("add new"));
    }

    public void selectContact() {
        clickContact(By.name("selected[]"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, null, null, null
                    , null, null);
            contacts.add(contact);
        }
        return contacts;
    }

//    public Contacts all() {
//        Contacts contacts = new Contacts();
//        List<WebElement> elements = wd.findElements(By.name("entry"));
//        for (WebElement element : elements) {
//            List<WebElement> cells = element.findElements(By.tagName("td"));
//            String lastname = cells.get(1).getText();
//            String firstname = cells.get(2).getText();
//            String[] phones = cells.get(5).getText().split("\n");
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            ContactData contact = new ContactData(id, firstname, lastname, null, null, phones[0], phones[1], phones[2]);
//            contacts.add(contact);
//        }
//        return contacts;
//    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, null, null, allPhones);
            contacts.add(contact);
        }
        return contacts;
    }

    public void clickModifyContactById(int id) {
        wd.findElement((By.cssSelector("a[href*='edit.php?id=" + id + "']"))).click();

    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String emailOne = wd.findElement(By.name("email")).getAttribute("value");
        String emailTwo = wd.findElement(By.name("email2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData(contact.getId(), firstname, lastname, null, address, homePhone, mobilePhone, workPhone);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s'", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement((By.tagName("a"))).click();

    }
}

