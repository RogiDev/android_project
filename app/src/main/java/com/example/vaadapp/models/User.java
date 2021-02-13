package com.example.vaadapp.models;

public class User {

    private String firstName, lastName, ID,email;

    public User(String firstName, String lastName, String ID,String email) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.ID=ID;
        this.email=email;
    }
    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", ID: " + ID;
    }
}