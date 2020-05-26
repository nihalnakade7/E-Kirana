package com.example.ekirana;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository
{
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application, String category){
        ProductDatabase database = ProductDatabase.getInstance(application);
        productDao = database.productDao();
        allProducts = productDao.getProducts(category);
    }

    public void insert(Product product)
    {
        new InsertNoteAsyncTAsk(productDao).execute(product);
    }

    public void update(Product product) {
        new UpdateProductAsyncTAsk(productDao).execute(product);
    }

    public void delete(Product product) {
        new DeleteProductAsyncTAsk(productDao).execute(product);
    }

    public LiveData<List<Product>> getAllProducts()
    {
        return allProducts;
    }

    private static class InsertNoteAsyncTAsk extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        public InsertNoteAsyncTAsk(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTAsk extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        public UpdateProductAsyncTAsk(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.update(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTAsk extends AsyncTask<Product, Void, Void>{

        private ProductDao productDao;

        public DeleteProductAsyncTAsk(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.delete(products[0]);
            return null;
        }
    }
}
