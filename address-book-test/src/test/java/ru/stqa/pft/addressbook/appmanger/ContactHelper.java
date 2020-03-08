package ru.stqa.pft.addressbook.appmanger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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
        typeContact(By.name("middlename"), contactData.getMiddlename());
        wd.findElement(By.name("company")).click();
        typeContact(By.name("company"), contactData.getCompany());
        typeContact(By.name("address"), contactData.getAdress());
        typeContact(By.name("home"), contactData.getPhone());
    }

    public void returnHomeContact() {
        clickContact(By.linkText("home page"));
    }

    public void submitContact() {
        clickContact(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void editContact (int index) {
        clickContact(By.linkText("home"));
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        //clickContact(By.xpath("//img[@alt='Edit']"));
    }


    public void updateContact() {
        clickContact(By.name("update"));
    }

    public void deleteContact() {
        clickContact(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void home() {
        clickContact(By.linkText("home"));
    }

    public void createContact (ContactData contact) {
        goToContact();
        fillContactInfo(contact);
        submitContact();
        returnHomeContact();
    }

    private void goToContact () {
        clickContact(By.linkText("add new"));
    }

    public void selectContact (int index) {
       wd.findElements(By.name("selected[]")).get(index).click();
    }

    public boolean isThereAContact () {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount () {
       return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList () {
        List<ContactData> contacts  = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("selected[]"));
      // List<WebElement> elements = wd.findElements(By.cssSelector("td.center > input"));
       // List<WebElement> elements = wd.findElements(By.tagName("td"));
    //   List<WebElement> elements = wd.findElements(By.name("tr[name=entry]"));
     //   List<WebElement> elements = wd.findElements(By.cssSelector("td.center"));
        for (WebElement element: elements) {
            String firstname = element.getText();
            String lastname = element.getText();
           // String firstname = element.findElement(By.tagName("td[1]")).getText();
           // String lastname = element.findElement(By.tagName("td[2]" )).getText();
            //int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            int id = Integer.parseInt(element.getAttribute("value"));
          //  int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, lastname,null,null,null, null);
            contacts.add(contact);

        }
        return contacts;
    }
}
