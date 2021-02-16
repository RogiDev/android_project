package com.example.vaadapp.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Apartment {

    private int apartmentNumber;
    private Person person;
    private String U_key;

    public Apartment(int apartmentNumber, Person person, String U_key){
        this.apartmentNumber=apartmentNumber;
        this.person=person;
        this.U_key=getKey();
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private String getKey(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        return(uid);
    }

}
