package com.example.ekirana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HorizontalRVAdapter extends RecyclerView.Adapter<HorizontalRVAdapter.RVHolder>
{
    Context mContext;
    List<ApiResponce> mList;

    public HorizontalRVAdapter(Context context, List<ApiResponce> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public HorizontalRVAdapter.RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.horizontal_rv_layout,parent,false);
        return new RVHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HorizontalRVAdapter.RVHolder holder, final int position) {
            holder.item_title.setText(mList.get(position).getCategory());
            List<ProductsApiResponse> productList = mList.get(position).getProductsList();
            SliderAdapter sliderAdapter = new SliderAdapter(mContext,productList);
            holder.recyclerView_hr.setHasFixedSize(true);
            holder.recyclerView_hr.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
            holder.recyclerView_hr.setAdapter(sliderAdapter);

            holder.recyclerView_hr.setNestedScrollingEnabled(false);

            holder.morebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext, "Button More", Toast.LENGTH_SHORT).show();
                    List<ProductsApiResponse> prodList = mList.get(position).getProductsList();;
                    Intent intent = new Intent(mContext,MoreProducts.class);
                    intent.putExtra("prodList",(Serializable) prodList);
                    mContext.startActivity(intent);

                }
            });
    }

    @Override
    public int getItemCount() {
        return (mList!= null ? mList.size() : 0);
    }

    public class RVHolder extends RecyclerView.ViewHolder {
        TextView item_title;
        RecyclerView recyclerView_hr;
        Button morebtn;
        public RVHolder(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.itemTitle);
            recyclerView_hr = itemView.findViewById(R.id.rv_horizontal);
             morebtn = itemView.findViewById(R.id.btnMore);
        }
    }
}
