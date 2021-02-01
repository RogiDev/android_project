package com.example.vaadapp;

public class BuildingCommittee extends Person {

    private String seniority;

    public BuildingCommittee(String firstName, String lastName, String ID, String seniority){
        super(firstName, lastName, ID);
        this.seniority=seniority;
    }

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
