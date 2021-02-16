package com.example.vaadapp.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

abstract class Person {

    private String firstName, lastName, email, UKey;
    private int ID;



    public Person(String firstName, String lastName, int ID, String UKey) {
        this.UKey=getKey();
        this.firstName=firstName;
        this.lastName=lastName;
        this.ID=ID;
    }

    public Person(){}

    private String getKey(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        return(uid);
    }

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", ID: " + ID;
    }


}