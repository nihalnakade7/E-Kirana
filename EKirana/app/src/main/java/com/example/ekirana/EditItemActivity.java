package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class EditItemActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView mDescription_tv;
    TextView mprice_tv;
    TextView mtotal_tv;
    NumberPicker units_picker;
    private int price;
    private int units;
    private String imgURL;
    private String description;
    private String name;
    List<CheckoutItems> listItems;
    public int EDIT_PRODUCT = 0;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        mImageView = findViewById(R.id.imageViewProduct_edtItem);
        mDescription_tv = findViewById(R.id.decsription_edtItem);
        mprice_tv = findViewById(R.id.textView_price_edtItem);
        mtotal_tv = findViewById(R.id.total_edtItem);
        units_picker = findViewById(R.id.numberPicker_units_edtItem);
        units_picker.setMinValue(1);
        units_picker.setMaxValue(10);

        Intent intent = getIntent();
        EDIT_PRODUCT = intent.getIntExtra("Edit_item",0);
        price = intent.getIntExtra("price",1);
        imgURL = intent.getStringExtra("imageURL");
        description = intent.getStringExtra("description");
        name = intent.getStringExtra("name");

        Picasso.get()
                .load("http://192.168.43.238/E-Kirana/"+imgURL)
                .fit()
                .into(mImageView);

        mDescription_tv.setText(description);
        mprice_tv.setText(String.valueOf(price));
        //int units = units_picker.getValue();
        mtotal_tv.setText(String.valueOf(price * units_picker.getValue()));
        if(EDIT_PRODUCT == 1)
        {
            position = intent.getIntExtra("position",0);
        }

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

    public void addItem(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.ekirana.CheckoutList",MODE_PRIVATE);
        getListFromSharedPreferences();
        units = units_picker.getValue();
        if(EDIT_PRODUCT == 0)
            listItems.add(new CheckoutItems(description,imgURL,price,units,name));
        else
            listItems.get(position).setUnits(units);

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(listItems);
        editor.putString("List_Items",json);
        editor.apply();

        Toast.makeText(this, "Items Added" + description, Toast.LENGTH_SHORT).show();
        finish();
    }


}
