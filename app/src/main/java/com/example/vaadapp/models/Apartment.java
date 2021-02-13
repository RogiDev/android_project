package com.example.vaadapp.models;

public class Apartment {

    private int apartmentNumber;
    private Person person;

    public Apartment(int apartmentNumber, Person person){
        this.apartmentNumber=apartmentNumber;
        this.person=person;
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

}
