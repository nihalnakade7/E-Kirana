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

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ProductsApiResponse> mList;
    private String[] imageList;
    private  ProductsApiResponse mApiResponse;

    public ViewPagerAdapter(Context context, List<ProductsApiResponse> response) {
        this.context = context;
        mList = response;
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
                .load("http://192.168.43.238/E-Kirana/"+mList.get(position).getImage_url())
                .fit()
                .into(imageView);
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+position);
                Intent intent = new Intent(context,SelectedProductActivity.class);
                intent.putExtra("price",mList.get(position).getPrice());
                intent.putExtra("imageURL",mList.get(position).getImage_url());
                intent.putExtra("description",mList.get(position).getDescription());
                intent.putExtra("name",mList.get(position).getName());
                context.startActivity(intent);
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

//    public  void startProductActivity(int position)
//    {
//
//    }
}
