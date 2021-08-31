package com.bnhp.CouponSystemPhase2.beans;

public enum Category {
    Food(1),
    Electricity(2),
    Restaurant(3),
    Vacation(4);

    private final int id;
    Category(int id){
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
