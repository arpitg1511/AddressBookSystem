package com.arpit.addressbook;

import com.arpit.addressbook.model.AddressBook;
import com.arpit.addressbook.model.Contact;
import com.arpit.addressbook.service.AddressBookService;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

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
            System.out.println("3. Search Person by City or State");
            System.out.println("4. View Person by City or State");
            System.out.println("5. Count Person by City or State");
            System.out.println("6. Save Address Books To File");
            System.out.println("7. Load Address Books From File");
            System.out.println("8. Save Address Books As CSV");
            System.out.println("9. Load Address Books From CSV");
            System.out.println("10. Save Address Books As JSON");
            System.out.println("11. Load Address Books From CSV");
            System.out.println("12. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createAddressBook(addressBookService);
                case 2 -> manageAddressBook(addressBookService);
                case 3 -> searchPersons(addressBookService);
                case 4 -> groupPersons(addressBookService);
                case 5 -> countPersons(addressBookService);
                case 6 -> saveToFile(addressBookService);
                case 7 -> loadFromFile(addressBookService);
                case 8 -> saveToCSV(addressBookService);
                case 9 -> loadFromCSV(addressBookService);
                case 10 -> saveToJSON(addressBookService);
                case 11 -> loadFromJSON(addressBookService);
                case 12 -> {
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
            System.out.println("5. Sort Contacts By Name");
            System.out.println("6. Sort Contacts By City or State or Zip");
            System.out.println("7. Back");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addContact(addressBook);
                case 2 -> editContact(addressBook);
                case 3 -> deleteContact(addressBook);
                case 4 -> addMultipleContacts(addressBook);
                case 5 -> sortContactsByName(addressBook);
                case 6 -> sortContacts(addressBook);
                case 7 -> managing = false;
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

        if(!addressBook.addContact(contact)) {
            System.out.println("Contact already exists!");
        } else {
            System.out.println("Contact added successfully!");
        }
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

            addContact(addressBook);

            System.out.print("Add another contact? (yes/no): ");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("yes")) {
                adding = false;
            }
        }
    }

    private static void searchPersons(AddressBookService addressBookService) {
        System.out.println("Search by : \n1. City\n2.State");

        String choice = scanner.nextLine();
        System.out.println("Enter name of city/state : ");
        String name = scanner.nextLine();
        List<Contact> searchList=
            switch (choice) {
                case "1" -> addressBookService.searchByCity(name);
                case "2" -> addressBookService.searchByState(name);
                default -> null;
            };

        if(searchList!=null) {
            if(!searchList.isEmpty()) {
                searchList.forEach(System.out::println);
            } else {
                System.out.println("No contact found!");
            }
        } else {
            System.out.println("Invalid state or city name entered!");
        }
    }

    private static void groupPersons(AddressBookService addressBookService) {
        System.out.println("View by : \n1. City\n2.State");

        String choice = scanner.nextLine();

        Map<String, List<Contact>> result =
                switch (choice) {
                    case "1" -> addressBookService.groupByCity();
                    case "2" -> addressBookService.groupByState();
                    default -> null;
                };

        if (result == null) {
            System.out.println("Invalid choice!");
            return;
        }

        if (result.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }

        result.forEach((key, contacts) -> {
            System.out.println("\n" + key.toUpperCase());
            contacts.forEach(System.out::println);
        });
    }

    private static void countPersons(AddressBookService addressBookService) {
        System.out.println("Count by : \n1. City\n2. State");
        String choice = scanner.nextLine();

        Map<String, Integer> result =
                switch(choice) {

            case "1" -> addressBookService.countByCity();
            case "2" -> addressBookService.countByState();
            default -> null;

                };

        if (result != null) {
            result.forEach((key, count) -> {
                System.out.println("\n" + key.toUpperCase());
                System.out.println(count);
            });
        }

        else
            System.out.print("Invalid Choice !");
    }

    private static void sortContactsByName(AddressBook addressBook) {
        addressBook.sortContactsByName();
        addressBook.getContacts().forEach(System.out::println);
    }

    private static void sortContacts(AddressBook addressBook) {
        System.out.println("Sort by \n1. City\n2. State\n3. Zip");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> addressBook.getContacts().sort(addressBook.cityComparator());
            case "2" -> addressBook.getContacts().sort(addressBook.stateComparator());
            case "3" -> addressBook.getContacts().sort(addressBook.zipComparator());
            default -> {System.out.println("Invalid choice !"); return;}
        }

        addressBook.getContacts().forEach(System.out::println);
    }

    private static void saveToFile(AddressBookService service) {

        try (java.io.PrintWriter writer =
                     new java.io.PrintWriter("addressbook.txt")) {

            service.getAddressBooks().forEach((bookName, book) -> {

                book.getContacts().forEach(contact -> {
                    writer.println(
                            bookName + "," +
                                    contact.getFirstName() + "," +
                                    contact.getLastName() + "," +
                                    contact.getAddress() + "," +
                                    contact.getCity() + "," +
                                    contact.getState() + "," +
                                    contact.getZip() + "," +
                                    contact.getPhone() + "," +
                                    contact.getEmail()
                    );
                });
            });

            System.out.println("Address Books saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadFromFile(AddressBookService service) {

        try (java.io.BufferedReader reader =
                     new java.io.BufferedReader(
                             new java.io.FileReader("addressbook.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String bookName = data[0];

                service.createAddressBook(bookName);

                Optional<AddressBook> opt =
                        service.getAddressBook(bookName);

                if (opt.isPresent()) {

                    AddressBook book = opt.get();

                    Contact contact = new Contact(
                            data[1], data[2], data[3],
                            data[4], data[5], data[6],
                            data[7], data[8]
                    );

                    book.addContact(contact);
                }
            }

            System.out.println("Address Books loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    private static void saveToCSV(AddressBookService service) {

        try (CSVWriter writer = new CSVWriter(new FileWriter("addressbook.csv"))) {

            service.getAddressBooks().forEach((bookName, book) -> {

                book.getContacts().forEach(contact -> {

                    String[] data = {
                            bookName,
                            contact.getFirstName(),
                            contact.getLastName(),
                            contact.getAddress(),
                            contact.getCity(),
                            contact.getState(),
                            contact.getZip(),
                            contact.getPhone(),
                            contact.getEmail()
                    };

                    writer.writeNext(data);
                });
            });

            System.out.println("Address Books saved as CSV successfully!");

        } catch (Exception e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    private static void loadFromCSV(AddressBookService service) {

        try (CSVReader reader = new CSVReader(new FileReader("addressbook.csv"))) {

            String[] data;

            while ((data = reader.readNext()) != null) {

                String bookName = data[0];

                service.createAddressBook(bookName);

                Optional<AddressBook> opt =
                        service.getAddressBook(bookName);

                if (opt.isPresent()) {

                    AddressBook book = opt.get();

                    Contact contact = new Contact(
                            data[1], data[2], data[3],
                            data[4], data[5], data[6],
                            data[7], data[8]
                    );

                    book.addContact(contact);
                }
            }

            System.out.println("Address Books loaded from CSV successfully!");

        } catch (Exception e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    private static void saveToJSON(AddressBookService service) {

        try (FileWriter writer = new FileWriter("addressbook.json")) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(service.getAddressBooks(), writer);

            System.out.println("Address Books saved as JSON successfully!");

        } catch (Exception e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    private static void loadFromJSON(AddressBookService service) {

        try (FileReader reader = new FileReader("addressbook.json")) {

            Gson gson = new Gson();

            Map<String, AddressBook> data =
                    gson.fromJson(reader,
                            new com.google.gson.reflect.TypeToken<
                                    Map<String, AddressBook>>(){}.getType());

            if (data != null) {
                service.getAddressBooks().clear();
                service.getAddressBooks().putAll(data);
            }

            System.out.println("Address Books loaded from JSON successfully!");

        } catch (Exception e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }
}