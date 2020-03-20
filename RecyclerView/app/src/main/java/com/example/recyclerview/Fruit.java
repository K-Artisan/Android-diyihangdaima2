package com.example.recyclerview;

public class Fruit {

    private String name;
    private int imageId;

    public Fruit(int imageId, String name){
        this.name = name;
        this.imageId = imageId;

    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
