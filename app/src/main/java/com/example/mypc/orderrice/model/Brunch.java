package com.example.mypc.orderrice.model;

/**
 * Created by MyPC on 2/10/2017.
 */

public class Brunch {
    private String food;
    private int idImageItems;

    public Brunch(String food, int idImageItems) {
        this.food = food;
        this.idImageItems = idImageItems;
    }

    public Brunch() {
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getIdImageItems() {
        return idImageItems;
    }

    public void setIdImageItems(int idImageItems) {
        this.idImageItems = idImageItems;
    }
}
