package com.appmaker.mvpdemo.Model;

public class DataModel {

    private String fName, fImage, category, size;
    private int price;

    public DataModel(String fName, String fImage, String category, String size) {
        this.fName = fName;
        this.fImage = fImage;
        this.category = category;
        this.size = size;
    }

    public String getfName() {
        return fName;
    }

    public String getfImage() {
        return fImage;
    }

    public String getCategory() {
        return category;
    }

    public String getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }
}
