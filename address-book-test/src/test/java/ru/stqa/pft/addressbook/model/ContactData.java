package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private String middlename;
    private final String company;
    private final String address;
    private String phone;
    private  String phoneHome;
    private  String phoneMobile;
    private  String phoneWork;
    private String allPhones;
    private String emailOne;
    private String emailTwo;
    private String allEmails;
    private int id;


    public ContactData(String firstName, String lastName, String company, String address, String phoneHome
            , String phoneMobile, String phoneWork) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;

    }

    public ContactData(int id, String firstName, String lastName, String company, String address, String phoneHome
            , String phoneMobile, String phoneWork) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
    }

    public ContactData(int id, String firstName, String lastName, String company, String address, String allPhones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.allPhones = allPhones;
    }

//    public ContactData(int id, String firstName, String lastName, String phoneHome, String phoneMobile, String phoneWork) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneHome = phoneHome;
//        this.phoneMobile = phoneMobile;
//        this.phoneWork = phoneWork;
//    }

    //
//    public ContactData(int id, String firstname, String lastname, String phoneHome
//            , String phoneMobile, String phoneWork) {
//        this.firstName = firstname;
//        this.lastName = lastname;
//        //       this.middlename = middlename;
////        this.company = company;
//        //       this.address = address;
//        this.phoneHome = phoneHome;
//        this.phoneMobile = phoneMobile;
//        this.phoneWork = phoneWork;
//        this.id = id;
////        this.allPhones = allPhones;
////        this.allEmails = allEmails;
////        this.emailOne = null;
////        this.emailTwo = null;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddlename() {
        return middlename;
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
