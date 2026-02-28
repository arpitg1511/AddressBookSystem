package com.arpit.addressbook.model;

import java.util.Objects;

public class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;

    public Contact(String firstName, String lastName, String address, String city, String state,
                   String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Address: " + address + " City: " + city +
                " State: " + state + " Zip: " + zip + " Phone: " + phone + " Email: " + email;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || !(other instanceof Contact contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return Objects.equals(firstName, otherContact.firstName) &&
                Objects.equals(lastName, otherContact.lastName) &&
                Objects.equals(address, otherContact.address) &&
                Objects.equals(city, otherContact.city) &&
                Objects.equals(state, otherContact.state) &&
                Objects.equals(zip, otherContact.zip) &&
                Objects.equals(phone, otherContact.phone) &&
                Objects.equals(email, otherContact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, state, zip, phone, email);
    }
}
