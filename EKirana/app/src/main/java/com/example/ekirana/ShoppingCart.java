package com.example.ekirana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ShoppingCart extends AppCompatActivity {
    private List<CheckoutItems> mItemsList;
    RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private SharedPreferences sharedPreferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mRecyclerView = findViewById(R.id.recyclerView);
        sharedPreferences = getSharedPreferences("com.example.ekirana.CheckoutList",MODE_PRIVATE);
        gson = new Gson();
        //displayList();

        //Connect ItemTouchHelper With Recycler view
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.checkout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Shopping Cart")
                        .setMessage("You Want to Place order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    CheckoutItems deletedItem;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP| ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END ,
            /*for onSwiped*/       ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT
    ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(mItemsList,fromPosition,toPosition);

            mRecyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            switch (direction)
            {
                case ItemTouchHelper.LEFT:
                    deletedItem =  mItemsList.get(position);
                    mItemsList.remove(position);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String json = gson.toJson(mItemsList);
                    editor.putString("List_Items",json);
                    editor.apply();
                    mAdapter.notifyItemRemoved(position);
                    Snackbar.make(mRecyclerView,deletedItem.getDescription()+"Deleted....",Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mItemsList.add(position,deletedItem);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    String json = gson.toJson(mItemsList);
                                    editor.putString("List_Items",json);
                                    editor.apply();
                                    mAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;
                case ItemTouchHelper.RIGHT:

                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //Third party library used to add background colour and Icons
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ShoppingCart.this,R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(ShoppingCart.this,R.color.green))
                    .addSwipeRightActionIcon(R.drawable.ic_done)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };



    void displayList()
    {

        Gson gson = new Gson();
        String json = sharedPreferences.getString("List_Items",null);
        Type type = new TypeToken<ArrayList<CheckoutItems>>(){}.getType();
        mItemsList = gson.fromJson(json,type);

        if(mItemsList == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);
            builder.setTitle("Alert")
                    .setMessage("Your Cart is Empty.. Please add some products :)")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }
        else
        {
            mAdapter = new RecyclerViewAdapter(mItemsList,this);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
