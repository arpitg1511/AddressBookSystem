package com.arpit.addressbook.service;

import com.arpit.addressbook.model.Contact;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

    @Test
    public void givenDB_whenRetrieved_shouldReturnContacts() {

        AddressBookService service = new AddressBookService();

        List<Contact> contacts = service.getContactsFromDB();

        assertNotNull(contacts);
        assertTrue(contacts.size() >= 0);

        contacts.forEach(System.out::println);
    }
}