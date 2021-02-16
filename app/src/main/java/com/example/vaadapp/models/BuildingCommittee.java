package com.example.vaadapp.models;



public class BuildingCommittee extends Tenant {

    private int role=0;

    public BuildingCommittee(String firstName, String lastName, int ID,String email, int apartmentNumber, int monthlyPayment){
        super(firstName, lastName, ID,email, apartmentNumber, monthlyPayment);
        this.role=0;
    }
    //public BuildingCommittee(){}

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        String fromTenant=super.toString();
        return fromTenant +
                "Role: " + role;
    }
}
