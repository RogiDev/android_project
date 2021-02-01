package com.example.vaadapp;

public class Tenant extends Person {

    private String apartmentNumber, monthlyPayment;

    public Tenant(String firstName, String lastName, String ID, String apartmentNumber, String monthlyPayment){
        super(firstName, lastName, ID);
        this.apartmentNumber=apartmentNumber;
        this.monthlyPayment=monthlyPayment;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        String fromPerson=super.toString();
        return fromPerson +
                "Apartment Number: " + apartmentNumber +
                ", Monthly Payment: " + monthlyPayment;
    }
}
