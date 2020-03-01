package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String middlename;
    private final String company;
    private final String adress;
    private final String phone;

    public ContactData(String firstname,String lastname, String middlename, String company, String adress, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.company = company;
        this.adress = adress;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
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

    @Override
    public String toString () {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode () {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
