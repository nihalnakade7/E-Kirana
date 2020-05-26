package com.example.ekirana;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoreProductsRecyclerViewAdapter extends RecyclerView.Adapter<MoreProductsRecyclerViewAdapter.MoreProductsViewHolder> {

    Context mContext;
    List<ProductsApiResponse> mList;

    public MoreProductsRecyclerViewAdapter(Context context, List<ProductsApiResponse> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public MoreProductsRecyclerViewAdapter.MoreProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.more_products_rv_item,parent,false);
        return new MoreProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreProductsRecyclerViewAdapter.MoreProductsViewHolder holder, final int position) {

        holder.title.setText(mList.get(position).getName());
        Picasso.get()
                .load("http://192.168.43.239/E-Kirana/"+mList.get(position).getImage_url())
                .fit()
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SelectedProductActivity.class);
                intent.putExtra("price",mList.get(position).getPrice());
                intent.putExtra("imageURL",mList.get(position).getImage_url());
                intent.putExtra("description",mList.get(position).getDescription());
                intent.putExtra("name",mList.get(position).getName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MoreProductsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView mImageView;
        public MoreProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.moreProducts_listItem_title_tv);
            mImageView = itemView.findViewById(R.id.moreProducts_listItem_imageView);
        }
    }
}
