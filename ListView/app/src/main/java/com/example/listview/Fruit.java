package com.example.listview;

public class Fruit {
    public Fruit(int imgId, String name) {
        this.imageId = imgId;
        this.name = name;
    }

    private int imageId;
    private String name;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
