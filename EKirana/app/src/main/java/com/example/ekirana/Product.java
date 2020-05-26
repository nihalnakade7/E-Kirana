package com.example.ekirana;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class Product
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String imgURL;
    private String description;
    private int price;
    private String category;

    public Product(int id, String name, String imgURL, String description, int price, String category) {
        this.id = id;
        this.name = name;
        this.imgURL = imgURL;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
