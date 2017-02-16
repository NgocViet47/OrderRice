package com.example.mypc.orderrice.model;

/**
 * Created by MyPC on 2/10/2017.
 */

public class Brunch {
    private String food;
    private int idBrunch;
    private int idImageItems;

    public Brunch(String food, int idBrunch, int idImageItems) {
        this.food = food;
        this.idBrunch = idBrunch;
        this.idImageItems = idImageItems;
    }

    public int getIdBrunch() {
        return idBrunch;
    }

    public void setIdBrunch(int idBrunch) {
        this.idBrunch = idBrunch;
    }

    public Brunch() {
    }

    public Brunch(int idBrunch) {
        this.idBrunch = idBrunch;
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
