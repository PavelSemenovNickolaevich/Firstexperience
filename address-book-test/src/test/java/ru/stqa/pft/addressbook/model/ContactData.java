package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contacts")
public class ContactData {
    @Expose
    private final String firstName;
    @Expose
    private String lastName;
    @Expose
    private String middlename;
    @Expose
    private  String company;
    @Expose
    private  String address;
    @Expose
    private String phone;
    @Expose
    private  String phoneHome;
    @Expose
    private  String phoneMobile;
    @Expose
    private  String phoneWork;
    @Expose
    private String allPhones;
    @Expose
    private String emailOne;
    @Expose
    private String emailTwo;
    @Expose
    private String allEmails;
    @XStreamOmitField
    private int id;
    private File photo;


    public ContactData(String firstName, String lastName, String company, String address, String phoneHome
            , String phoneMobile, String phoneWork, String emailOne, String emailTwo) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;

    }

    public ContactData(int id, String firstName, String lastName, String company, String address, String phoneHome
            , String phoneMobile, String phoneWork, String emailOne, String emailTwo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
        this.emailOne = emailTwo;
        this.emailTwo = emailOne;
    }


    public ContactData(int id, String firstName, String lastName, String company, String address, String allPhones, String allEmails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.allPhones = allPhones;
        this.allEmails = allEmails;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public void setPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
    }

    public void setAllPhones(String allPhones) {
        this.allPhones = allPhones;
    }

    public void setEmailOne(String emailOne) {
        this.emailOne = emailOne;
    }

    public void setEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
    }

    public void setAllEmails(String allEmails) {
        this.allEmails = allEmails;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

}
