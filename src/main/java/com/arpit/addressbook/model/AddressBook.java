package com.arpit.addressbook.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }
    public AddressBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean addContact(Contact contact) {
        Optional<Contact> opt = contacts.stream()
                                .filter(c -> c.equals(contact))
                                .findFirst();

        if(opt.isEmpty()) {
            return false;
        } else {
            contacts.add(contact);
            return true;
        }
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

    public boolean deleteContact(String firstName, String lastName) {
        return contacts.removeIf(c -> c.getFirstName().equals(firstName)
                                    && c.getLastName().equals(lastName));
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void sortContactsByName() {
        contacts.sort(Comparator
                .comparing(Contact::getFirstName, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contact::getLastName, String.CASE_INSENSITIVE_ORDER));
    }

    public Comparator<Contact> zipComparator() {
        return Comparator.comparing(Contact::getZip);
    }

    public Comparator<Contact> cityComparator() {
        return Comparator.comparing(Contact::getCity);
    }

    public Comparator<Contact> stateComparator() {
        return Comparator.comparing(Contact::getState);
    }
}
