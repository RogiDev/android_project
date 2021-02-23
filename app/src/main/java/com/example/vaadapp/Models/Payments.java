package com.example.vaadapp.Models;

public class Payments {
    private String month,userId,_id;
    private int amount;

    public Payments(String month, String userId, String _id, int amount) {
        this.month = month;
        this.userId = userId;
        this._id = _id;
        this.amount = amount;
    }

    public Payments() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmonut() {
        return amount;
    }

    public void setAmonut(int amonut) {
        this.amount = amonut;
    }
}
