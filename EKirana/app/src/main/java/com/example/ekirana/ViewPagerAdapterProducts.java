package com.example.ekirana;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapterProducts extends PagerAdapter
{

    private Context context;
    private List<Product> mList;
    private String[] imageList;
    private  Product mProduct;

    public ViewPagerAdapterProducts(Context context, List<Product> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load("http://192.168.43.238/E-Kirana/"+mList.get(position).getImgURL())
                .fit()
                .into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+position);
                Intent intent = new Intent(context,SelectedProductActivity.class);
                intent.putExtra("price",mList.get(position).getPrice());
                intent.putExtra("imageURL",mList.get(position).getImgURL());
                intent.putExtra("description",mList.get(position).getDescription());
                context.startActivity(intent);
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
