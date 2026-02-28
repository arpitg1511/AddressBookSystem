package com.arpit.addressbook;

import com.arpit.addressbook.model.AddressBook;
import com.arpit.addressbook.model.Contact;

import java.util.Optional;
import java.util.Scanner;

public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book");

        AddressBook addressBook = new AddressBook();

        boolean running = true;

        while (running) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addContact(addressBook);
                case 2 -> editContact(addressBook);
                case 3 -> deleteContact(addressBook);
                case 4 -> {
                    running = false;
                    System.out.println("Exiting Address Book...");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ================== UC1 ==================

    private static void addContact(AddressBook addressBook) {

        System.out.println("\nEnter Contact Details:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("State: ");
        String state = scanner.nextLine();

        System.out.print("Zip: ");
        String zip = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(
                firstName,
                lastName,
                address,
                city,
                state,
                zip,
                phoneNumber,
                email
        );

        addressBook.addContact(contact);

        System.out.println("Contact added successfully!");
    }

    // ================== UC2 ==================

    private static void editContact(AddressBook addressBook) {

        System.out.println("\nEnter name of contact to edit:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        Optional<Contact> contactOpt = addressBook.findContact(firstName, lastName);

        if (contactOpt.isPresent()) {

            Contact contact = contactOpt.get();

            System.out.println("Contact found. Enter new details:");

            System.out.print("New Address: ");
            contact.setAddress(scanner.nextLine());

            System.out.print("New City: ");
            contact.setCity(scanner.nextLine());

            System.out.print("New State: ");
            contact.setState(scanner.nextLine());

            System.out.print("New Zip: ");
            contact.setZip(scanner.nextLine());

            System.out.print("New Phone Number: ");
            contact.setPhone(scanner.nextLine());

            System.out.print("New Email: ");
            contact.setEmail(scanner.nextLine());

            System.out.println("Contact updated successfully!");

        } else {
            System.out.println("Contact not found!");
        }
    }

    public static void deleteContact(AddressBook addressBook) {

        System.out.println("\nEnter name of contact to delete:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        if(addressBook.deleteContact(firstName, lastName)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }
}