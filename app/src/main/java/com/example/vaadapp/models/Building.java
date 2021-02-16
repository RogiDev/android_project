package com.example.vaadapp.models;

import com.example.vaadapp.models.Apartment;

import java.util.ArrayList;

public class Building {

    private static ArrayList<Apartment> arrApartment;
    private int buildingNumber;
    private BuildingCommittee buildingCommittee;
    private int MAXapartmentNumber;
    private String Address;

    public Building(int buildingNumber, BuildingCommittee buildingCommittee, int MAXapartmentNumber, String Address){
        if(this.buildingCommittee.getRole()==0){
            this.buildingNumber=buildingNumber;
            this.buildingCommittee=buildingCommittee;
            this.MAXapartmentNumber=MAXapartmentNumber;
            this.arrApartment=new ArrayList<Apartment>(MAXapartmentNumber);
            this.Address=Address;
        }
    }

    //GET&SET

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public static ArrayList<Apartment> getArrApartment() {
        return arrApartment;
    }

    public void setArrApartment(ArrayList<Apartment> arrApartment) {
        this.arrApartment = arrApartment;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public BuildingCommittee getBuildingCommittee() {
        return buildingCommittee;
    }

    public void setBuildingCommittee(BuildingCommittee buildingCommittee) {
        this.buildingCommittee = buildingCommittee;
    }

    public int getMAXapartmentNumber() {
        return MAXapartmentNumber;
    }

    public void setMAXapartmentNumber(int MAXapartmentNumber) {
        this.MAXapartmentNumber = MAXapartmentNumber;
    }


    //methods

    public boolean addApartment(Apartment arrApartment){
        if(this.arrApartment.size()<this.MAXapartmentNumber){
            this.arrApartment.add(arrApartment);
            return true;
        }
        return false;
    }



}
