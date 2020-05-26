package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MoreProducts extends AppCompatActivity {

    List<ProductsApiResponse> mList;
    RecyclerView mRecyclerView;
    MoreProductsRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_products);
        mRecyclerView = findViewById(R.id.moreProducts_rv);

        Intent intent = getIntent();
        mList = (List<ProductsApiResponse>) intent.getExtras().getSerializable("prodList");
        if (mList != null)
            Toast.makeText(this, mList.get(0).getCategory(), Toast.LENGTH_SHORT).show();


        adapter = new MoreProductsRecyclerViewAdapter(this,mList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(adapter);
    }
}
