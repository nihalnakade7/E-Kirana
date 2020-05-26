package com.example.ekirana;

import java.util.List;

public class ApiResponce
{

        private List<ProductsApiResponse> ProductsList;
        String category;

    public ApiResponce() {
    }

    public ApiResponce(List<ProductsApiResponse> productsList, String category) {
            ProductsList = productsList;
            this.category = category;
        }

        public List<ProductsApiResponse> getProductsList() {
            return ProductsList;
        }

        public void setProductsList(List<ProductsApiResponse> productsList) {
            ProductsList = productsList;
        }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

