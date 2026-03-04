package com.arpit.addressbook.io;

import com.arpit.addressbook.model.AddressBook;
import com.arpit.addressbook.model.Contact;

import java.io.*;
import java.util.List;

public class AddressBookFileService {

    public void readFromFile(AddressBook addressBook, String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                Contact contact = new Contact(
                        parts[0], parts[1], parts[2],
                        parts[3], parts[4], parts[5],
                        parts[6], parts[7]
                );

                addressBook.addContact(contact);
            }

            System.out.println("Address Book loaded from file successfully.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void writeToFile(AddressBook addressBook, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Contact contact : addressBook.getContacts()) {
                writer.write(formatContact(contact));
                writer.newLine();
            }

            System.out.println("Address Book saved to file successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private String formatContact(Contact contact) {
        return String.join(",",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getAddress(),
                contact.getCity(),
                contact.getState(),
                contact.getZip(),
                contact.getPhone(),
                contact.getEmail()
        );
    }
}