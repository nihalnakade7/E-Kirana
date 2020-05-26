package com.example.ekirana;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectedProductAdapter extends  RecyclerView.Adapter<SelectedItemViewHolder>
{

    public List<CheckoutItems> mItemsList;
    private Context mContext;
    private onButtonClickListener mListener;

    public SelectedProductAdapter(List<CheckoutItems> itemsList, Context context, onButtonClickListener listener) {
        mItemsList = itemsList;
        mContext = context;
        mListener = listener;
    }

    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.selected_product_list_item,parent,false);
        return new SelectedItemViewHolder(listItem,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        holder.descriptyion_tv.setText(mItemsList.get(position).getDescription());
        holder.price_tv.setText(String.valueOf(mItemsList.get(position).getPrice()));
        Picasso.get()
                .load("http://192.168.43.239/E-Kirana/"+mItemsList.get(position).getImageURL())
                .fit()
                .into(holder.mImageView);
        Log.d("TAG", "onBindViewHolder: "+ mItemsList.get(position).getImageURL());
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }


    public interface onButtonClickListener{
        void onButtonClick(int position,String units);
    }
}
