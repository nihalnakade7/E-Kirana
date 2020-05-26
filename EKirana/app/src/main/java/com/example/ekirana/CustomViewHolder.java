package com.example.ekirana;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView descriptyion_tv;
    TextView price_tv;
    TextView units_tv;
    TextView total_tv;
    ImageView mImageView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.descriptyion_tv = itemView.findViewById(R.id.description_rv);
        this.price_tv = itemView.findViewById(R.id.price_rv);
        this.units_tv = itemView.findViewById(R.id.units_rv);
        this.total_tv = itemView.findViewById(R.id.total_rv);
        mImageView = itemView.findViewById(R.id.imageView_rv);

    }

}
