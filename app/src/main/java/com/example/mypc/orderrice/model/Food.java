package com.example.mypc.orderrice.model;

/**
 * Created by MyPC on 2/10/2017.
 */

public class Food {
    private int idName;
    private int idImage;
    private String name;
    private int idBrunchName;
    private int quantity;
    private int value;
    private int checkInt;

    public Food(int idName, int idImage, String name, int idBrunchName, int quantity, int value, int checkInt) {
        this.idName = idName;
        this.idImage = idImage;
        this.name = name;
        this.idBrunchName = idBrunchName;
        this.quantity = quantity;
        this.value = value;
        this.checkInt = checkInt;
    }

    public Food(int idName) {
        this.idName = idName;
    }

    public int getIdName() {
        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public Food() {
    }

    public int getIdBrunchName() {
        return idBrunchName;
    }

    public void setIdBrunchName(int idBrunchName) {
        this.idBrunchName = idBrunchName;
    }

    public int getCheckInt() {
        return checkInt;
    }

    public void setCheckInt(int checkInt) {
        this.checkInt = checkInt;
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
        if(quantity>15) {
            this.quantity = 15;
        }
        else this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
