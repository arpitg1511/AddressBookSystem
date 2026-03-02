package com.arpit.addressbook;

import com.arpit.addressbook.model.AddressBook;
import com.arpit.addressbook.model.Contact;
import com.arpit.addressbook.service.AddressBookService;

import java.util.Optional;
import java.util.Scanner;

public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book System");
        AddressBookService addressBookService = new AddressBookService();

        boolean running = true;

        while (running) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Create AddressBook");
            System.out.println("2. Select AddressBook");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createAddressBook(addressBookService);
                case 2 -> manageAddressBook(addressBookService);
                case 3 -> {
                    running = false;
                    System.out.println("Exiting Address Book System...");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ================== ADDRESS BOOK LEVEL ==================

    private static void createAddressBook(AddressBookService service) {

        System.out.print("Enter Address Book Name: ");
        String name = scanner.nextLine();

        if (service.createAddressBook(name)) {
            System.out.println("Address Book created successfully!");
        } else {
            System.out.println("Address Book with this name already exists!");
        }
    }

    private static void manageAddressBook(AddressBookService service) {

        System.out.print("Enter Address Book Name: ");
        String name = scanner.nextLine();

        Optional<AddressBook> opt = service.getAddressBook(name);

        if (opt.isEmpty()) {
            System.out.println("Address Book not found!");
            return;
        }

        AddressBook addressBook = opt.get();

        boolean managing = true;

        while (managing) {

            System.out.println("\nManaging AddressBook: " + name);
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Add Multiple Contacts");
            System.out.println("5. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addContact(addressBook);
                case 2 -> editContact(addressBook);
                case 3 -> deleteContact(addressBook);
                case 4 -> addMultipleContacts(addressBook);
                case 5 -> managing = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ================== CONTACT LEVEL ==================

    private static Contact readContactFromConsole() {

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

        return new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }

    private static void addContact(AddressBook addressBook) {

        Contact contact = readContactFromConsole();
        addressBook.addContact(contact);

        System.out.println("Contact added successfully!");
    }

    private static void editContact(AddressBook addressBook) {

        System.out.println("\nEnter name of contact to edit:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        Optional<Contact> contactOpt = addressBook.findContact(firstName, lastName);

        if (contactOpt.isPresent()) {

            Contact contact = contactOpt.get();

            System.out.println("Enter new details:");

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

    private static void deleteContact(AddressBook addressBook) {

        System.out.println("\nEnter name of contact to delete:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        if (addressBook.deleteContact(firstName, lastName)) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private static void addMultipleContacts(AddressBook addressBook) {

        boolean adding = true;

        while (adding) {

            Contact contact = readContactFromConsole();
            addressBook.addContact(contact);

            System.out.print("Add another contact? (yes/no): ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                adding = false;
            }
        }
    }
}