package com.arpit.addressbook.service;

import com.arpit.addressbook.model.AddressBook;

import java.util.*;

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
}
