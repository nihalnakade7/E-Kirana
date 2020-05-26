package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectedProductActivity extends AppCompatActivity implements SelectedProductAdapter.onButtonClickListener {

    private int price;
    private int units;
    private String imgURL;
    private String description;
    private String name;
    private RecyclerView mRecyclerView;
    SelectedProductAdapter adapter;
    List<CheckoutItems> listItems;
    public int EDIT_PRODUCT = 0;
    int position;
    Retrofit retrofit;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_product);

        mRecyclerView = findViewById(R.id.recyclerViewSeletedItem);
        Intent intent = getIntent();
        price = intent.getIntExtra("price",1);
        imgURL = intent.getStringExtra("imageURL");
        description = intent.getStringExtra("description");
        name = intent.getStringExtra("name");
        Log.d("TAG", "onCreate: "+ name);
        fetchProductsByName();

    }

    void getListFromSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.ekirana.CheckoutList",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("List_Items",null);
        Type type = new TypeToken<ArrayList<CheckoutItems>>(){}.getType();
        listItems = gson.fromJson(json,type);

        if (listItems == null)
        {
            listItems = new ArrayList<>();
        }
    }

    public void addItem()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.ekirana.CheckoutList",MODE_PRIVATE);
        getListFromSharedPreferences();
            listItems.add(new CheckoutItems(description,imgURL,price,units,name));

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(listItems);
        editor.putString("List_Items",json);
        editor.apply();
        Toast.makeText(this, "Items Added" + description, Toast.LENGTH_SHORT).show();
    }


    public void fetchProductsByName()
    {
        gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit =  new retrofit2.Retrofit.Builder()
            .baseUrl("http://192.168.43.239/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        Api api = retrofit.create(Api.class);
        Call<List<CheckoutItems>> call = api.getProductsByName(name);
        call.enqueue(new Callback<List<CheckoutItems>>() {
            @Override
            public void onResponse(Call<List<CheckoutItems>> call, Response<List<CheckoutItems>> response) {
                    generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<CheckoutItems>> call, Throwable t) {
                networkOperationFaild();
            }
        });

    }

    private void generateDataList(List<CheckoutItems> photoList) {
        listItems = photoList;
        Toast.makeText(this, "generateDataList"+photoList.size(), Toast.LENGTH_SHORT).show();

         adapter = new SelectedProductAdapter(photoList,getApplicationContext(),this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        //Toast.makeText(this, "Adapter Attached", Toast.LENGTH_SHORT).show();
    }

    private void networkOperationFaild()
    {
        Toast.makeText(this, "Could Not fetch Data Please check Your Connectivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(int position,String val) {
         units = Integer.parseInt(val);
         addItem();
         listItems.remove(position);
         adapter.notifyItemRemoved(position);
        Log.d("TAG", "onButtonClick: "+ units);
        Toast.makeText(this, val, Toast.LENGTH_SHORT).show();

    }
}
