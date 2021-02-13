package com.example.vaadapp.models;



public class BuildingCommittee extends User {

    private String seniority;

    public BuildingCommittee(String firstName, String lastName, String ID,String email, String seniority){
        super(firstName, lastName, ID,email);
        this.seniority=seniority;
    }
    public BuildingCommittee(){}

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    @Override
    public String toString() {
        String fromPerson=super.toString();
        return fromPerson +
                "Seniority: " + seniority;
    }
}
