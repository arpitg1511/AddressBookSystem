package com.arpit.addressbook.repository;

import com.arpit.addressbook.model.Contact;
import com.arpit.addressbook.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public List<Contact> getContacts() {

        List<Contact> contacts = new ArrayList<>();

        String query = "select * from contacts";

        try(Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("state"),
                        resultSet.getString("zip"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );

                contacts.add(contact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
