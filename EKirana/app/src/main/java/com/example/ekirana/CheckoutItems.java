package com.example.ekirana;

public class CheckoutItems
{
    private String description;
    private String image_url;
    private int price;
    private int units;
    private String name;

    public CheckoutItems(String description, String imageURL, int price, int units,String name) {
        this.description = description;
        this.image_url = imageURL;
        this.price = price;
        this.units = units;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return image_url;
    }

    public void setImageURL(String imageURL) {
        this.image_url = imageURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
