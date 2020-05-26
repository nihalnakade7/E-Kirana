package com.example.ekirana;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao
{
    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM product_table WHERE category = :Category")
    LiveData<List<Product>> getProducts(String Category);

    @Query("SELECT * FROM product_table WHERE  name = :name")
    LiveData<List<Product>> getProductsByName(String name);
}
