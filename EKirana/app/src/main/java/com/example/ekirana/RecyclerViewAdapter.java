package com.example.ekirana;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<CheckoutItems> mItemsList;
    private Context mContext;

    public RecyclerViewAdapter(List<CheckoutItems> itemsList, Context context) {
        mItemsList = itemsList;
        mContext = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.recyclerview_listitem,parent,false);
            return new CustomViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.descriptyion_tv.setText(mItemsList.get(position).getDescription());
        holder.price_tv.setText(String.valueOf(mItemsList.get(position).getPrice()));
        holder.units_tv.setText(String.valueOf(mItemsList.get(position).getUnits()));
        holder.total_tv.setText(String.valueOf(mItemsList.get(position).getUnits() * mItemsList.get(position).getPrice() ));
        Picasso.get()
                .load("http://192.168.43.238/E-Kirana/"+mItemsList.get(position).getImageURL())
                .fit()
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Item clicked"+mItemsList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,EditItemActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("Edit_item",1);
                intent.putExtra("price",mItemsList.get(position).getPrice());
                intent.putExtra("description",mItemsList.get(position).getDescription());
                intent.putExtra("imageURL",mItemsList.get(position).getImageURL());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }
}
