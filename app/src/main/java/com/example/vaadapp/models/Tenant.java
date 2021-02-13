package com.example.vaadapp.models;

public class Tenant extends User {

    private String apartmentNumber, monthlyPayment;

    public Tenant(String firstName, String lastName, String ID,String email, String apartmentNumber, String monthlyPayment){
        super(firstName, lastName, ID,email);
        this.apartmentNumber=apartmentNumber;
        this.monthlyPayment=monthlyPayment;
    }
    public Tenant(){}

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
