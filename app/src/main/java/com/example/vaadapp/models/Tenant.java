package com.example.vaadapp.models;

public class Tenant extends Person {

    private int apartmentNumber, monthlyPayment, role=1;

    public Tenant(String firstName, String lastName, int ID, String email, int apartmentNumber, int monthlyPayment){
        super(firstName, lastName, ID,email);
        this.apartmentNumber=apartmentNumber;
        this.monthlyPayment=monthlyPayment;
        this.role=1;
    }
    public Tenant(){}

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        String fromPerson=super.toString();
        return fromPerson +
                "Apartment Number: " + apartmentNumber +
                ", Monthly Payment: " + monthlyPayment +
                ", Role: " + role;
    }
}
