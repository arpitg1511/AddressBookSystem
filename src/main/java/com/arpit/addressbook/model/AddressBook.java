package com.arpit.addressbook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }
    public AddressBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean containsContact(Contact contact) {
        return contacts.contains(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Optional<Contact> findContact(String firstName, String lastName) {
        return contacts.stream()
                .filter(c -> c.getFirstName().equals(firstName) && c.getLastName().equals(lastName))
                .findFirst();
    }
}
