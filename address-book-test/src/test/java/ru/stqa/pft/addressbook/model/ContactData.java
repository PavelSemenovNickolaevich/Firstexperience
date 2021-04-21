package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contacts")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Transient
    private String middlename;
    @Expose
    @Column(name = "company")
    private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Transient
    private String phone;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String phoneHome;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String phoneMobile;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String phoneWork;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String emailOne;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String emailTwo;
    @Expose
    @Transient
    private String allEmails;
    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id;
    @Transient
    // @Column(name = "photo")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public ContactData(int id, String firstName, String lastName, String company, String address, String phoneHome
            , String phoneMobile, String phoneWork, String emailOne, String emailTwo) {
        this.id = id;
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

    public ContactData() {
    }


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


    public Groups getGroup() {
        return new Groups(groups);
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
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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
