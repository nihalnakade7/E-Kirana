package com.example.ekirana;

import java.io.Serializable;

public class ProductsApiResponse implements Serializable
{
    private int id;
    private String name;
    private String description;
    private int price;
    private String image_url;
    private String msg;
    private String category;



    public ProductsApiResponse(int id, String name, String description, int price, String image_url, String msg, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image_url = image_url;
        this.msg = msg;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
