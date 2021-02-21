package com.example.vaadapp.Models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Building {
    private ArrayList<Apartment> arrApartment;
    private int buildingNumber;
    private String manager;
    private int maxApartements;
    private String _id;
    private String address,entry;


    public Building(int buildingNumber, String manager, int maxApartmentNumber,String entry ,String address, String _id) {
        this.buildingNumber = buildingNumber;
        this.manager = manager;
        this.maxApartements = maxApartmentNumber;
        this.arrApartment = new ArrayList<>(maxApartmentNumber);
        this._id = _id;
        this.address = address;
        this.entry = entry;
    }

    public Building() {
    }

    public int getMaxApartements() {
        return maxApartements;
    }

    public void setMaxApartements(int maxApartements) {
        this.maxApartements = maxApartements;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    //GET&SET
    public ArrayList<Apartment> getArrApartment() {
        return arrApartment;
    }

    public void setArrApartment(ArrayList<Apartment> arrApartment) {
        this.arrApartment = arrApartment;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String get_id() {
        return _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getManager() {
        return this.manager;
    }


    public int getMaxApartmentNumber() {
        return this.maxApartements;
    }

    public void setMaxApartmentNumber(int maxApartmentNumber) {
        this.maxApartements = maxApartmentNumber;
    }


    //methods

    public boolean addApartment(Apartment arrApartment) {
        if (this.arrApartment.size() < this.maxApartements) {
            this.arrApartment.add(arrApartment);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Address: " + this.getAddress() + ", Building Number: " + this.getBuildingNumber();
    }
}
