package com.arpit.addressbook.service;
import com.arpit.addressbook.model.AddressBook;
import com.arpit.addressbook.model.Contact;

import java.util.*;
import java.util.stream.*;

public class AddressBookService {
    private Map<String, AddressBook> addressBooks;

    public AddressBookService() {
        addressBooks = new HashMap<>();
    }

    public boolean createAddressBook(String id) {

        Optional<String> opt = addressBooks.keySet()
                .stream().filter(k -> k.equals(id)).findFirst();

        if(opt.isPresent()) {
            return false;
        }

        addressBooks.put(id, new AddressBook());
        return true;
    }

    public Optional<AddressBook> getAddressBook(String id) {
        return Optional.ofNullable(addressBooks.get(id));
    }

    public Map<String, AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public List<Contact> searchByCity(String city) {
        return addressBooks.values().stream()
                .flatMap(book -> book.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByState(String state) {
        return addressBooks.values().stream()
                .flatMap(b -> b.getContacts().stream())
                .filter(c -> c.getState().equals(state))
                .collect(Collectors.toList());
    }

    public Map<String, List<Contact>> groupByCity() {
        return addressBooks.values().stream()
                .flatMap(b -> b.getContacts().stream())
                .collect(
                        Collectors.groupingBy(Contact::getCity)
                );
    }

    public Map<String, List<Contact>> groupByState() {
        return addressBooks.values().stream()
                .flatMap(b -> b.getContacts().stream())
                .collect(
                        Collectors.groupingBy(Contact::getState)
                );
    }

    public Map<String, Integer> countByCity() {
        return addressBooks.values().stream()
                .flatMap(b -> b.getContacts().stream())
                .collect(
                        Collectors.groupingBy(Contact::getCity,
                                Collectors.collectingAndThen(
                                        Collectors.counting(),
                                        Long::intValue
                                ))
                );
    }

    public Map<String, Integer> countByState() {
        return addressBooks.values().stream()
                .flatMap(b -> b.getContacts().stream())
                .collect(
                        Collectors.groupingBy(Contact::getState,
                                Collectors.collectingAndThen(
                                        Collectors.counting(),
                                        Long::intValue
                                ))
                );
    }
}
