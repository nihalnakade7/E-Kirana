package com.example.ekirana;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderViewHolder> {

    private Context context;
    private List<ProductsApiResponse> mList;

    public SliderAdapter(Context context, List<ProductsApiResponse> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.slider_list_item,parent,false);
        return new SliderViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, final int position) {
        Picasso.get()
                .load("http://192.168.43.239/E-Kirana/"+mList.get(position).getImage_url())
                .fit()
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+ position);
                Intent intent = new Intent(context,SelectedProductActivity.class);
                intent.putExtra("price",mList.get(position).getPrice());
                intent.putExtra("imageURL",mList.get(position).getImage_url());
                intent.putExtra("description",mList.get(position).getDescription());
                intent.putExtra("name",mList.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
