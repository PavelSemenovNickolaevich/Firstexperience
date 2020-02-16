package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String company;
    private final String adress;
    private final String phone;

    public ContactData(String firstname, String middlename, String company, String adress, String phone) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.company = company;
        this.adress = adress;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getCompany() {
        return company;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }
}
