package ru.stqa.pft.addressbook.model;

public class ContactData {

    private int id;
    private final String firstname;
    private final String lastname;
    private final String middlename;
    private final String company;
    private final String adress;
    private final String phoneHome;
    private final String phoneMobile;
    private final String phoneWork;
    private final String emailOne;
    private final String emailTwo;
    private String title;
    private String allEmails;

    public ContactData (String firstname, String lastname, String middlename, String company, String adress,
                        String phoneHome, String phoneMobile, String phoneWork, String emailOne, String emailTwo) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.company = company;
        this.adress = adress;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;
    }

    public ContactData (int id, String firstname, String lastname, String middlename, String company, String adress,
                        String phoneHome, String phoneMobile, String phoneWork, String emailOne, String emailTwo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.company = company;
        this.adress = adress;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
        this.id = id;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;
    }

    public ContactData (int id, String firstname, String lastname, String middlename, String company, String adress,
                        String phoneHome, String phoneMobile, String phoneWork, String allEmails) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.company = company;
        this.adress = adress;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.phoneWork = phoneWork;
        this.id = id;
        this.allEmails = allEmails;
        this.emailOne = null;
        this.emailTwo = null;
    }
    public String getEmailOne() {
        return emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public String getFirstname () {
        return firstname;
    }

    public String getLastName () {
        return lastname;
    }

    public String getMiddlename () {
        return middlename;
    }

    public String getCompany () {
        return company;
    }

    public String getAdress () {
        return adress;
    }

    public String getPhoneHome () {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public int getId () {
        return id;
    }

    public String getAllEmails () {
        return allEmails;
    }

    public void allEmails (String allEmails) {
        this.allEmails = allEmails;
    }

    @Override
    public String toString () {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode () {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public void setId (int id) {
        this.id = id;
    }

}
