package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void logoutContact() {
        clickContact(By.linkText("Logout"));
    }

    public void fillContactInfo(ContactData contactData) {
        typeContact(By.name("firstname"), contactData.getFirstname());
        typeContact(By.name("lastname"), contactData.getLastName());
        wd.findElement(By.name("company")).click();
        typeContact(By.name("company"), contactData.getCompany());
        typeContact(By.name("address"), contactData.getAddress());
        typeContact(By.name("home"), contactData.getPhone());
    }

    public void returnHomeContact() {
        clickContact(By.linkText("home page"));
    }

    public void submitContact() {
        clickContact(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void editContact() {
        clickContact(By.linkText("home"));
        clickContact(By.xpath("//img[@alt='Edit']"));
    }

    public void updateContact() {
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
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }


}
