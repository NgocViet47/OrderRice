package com.example.mypc.orderrice.model;

/**
 * Created by MyPC on 2/10/2017.
 */

public class Food {
    private int idImage;
    private String name;
    private int quantity;
    private int value;

    public Food(int idImage, String name, int quantity, int value) {
        this.idImage = idImage;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    public Food() {
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if(this.quantity>15){
            this.quantity = 15;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
