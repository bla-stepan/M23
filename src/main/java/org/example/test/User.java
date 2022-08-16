package org.example.test;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;

    public User() {}

    public User(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("User[id%s, firstname=%s, lastname=%s, phone=%s, email=%s]", id, firstName, lastName, phone, email);
    }
}

