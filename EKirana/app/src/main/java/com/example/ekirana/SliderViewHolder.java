package com.example.ekirana;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class SliderViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    public SliderViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mImageView = itemView.findViewById(R.id.slider_imageView);
    }
}
