package com.example.ekirana;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application, String category) {
        super(application);
        repository = new ProductRepository(application,category);
        allProducts = repository.getAllProducts();
    }

    public void insert(Product product)
    {
        repository.insert(product);
    }

    public void update(Product product){
        repository.update(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }

    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }

}
