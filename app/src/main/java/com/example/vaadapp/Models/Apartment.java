package com.example.vaadapp.Models;



public class Apartment {

    private int apartmentNumber;;
    private String user;
    private int floor;
    private String _id;
    public Apartment(int apartmentNumber, int floor,String user,String _id){
        this.apartmentNumber=apartmentNumber;
        this.user=user;
        this.floor = floor;
        this._id = _id;
    }

    public Apartment(){}

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getUser() {
        return this.user;
    }

    public void setPerson(String user
    ) {
        this.user = user;
    }

}