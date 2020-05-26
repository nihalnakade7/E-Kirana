package com.example.ekirana.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ekirana.Api;
import com.example.ekirana.ApiResponce;
import com.example.ekirana.HorizontalRVAdapter;
import com.example.ekirana.MainActivity;
import com.example.ekirana.Product;
import com.example.ekirana.ProductViewModel;
import com.example.ekirana.ProductViewModelFactory;
import com.example.ekirana.ProductsAcivity;
import com.example.ekirana.ProductsApiResponse;
import com.example.ekirana.R;
import com.example.ekirana.RetrofitClientInstance;
import com.example.ekirana.ShoppingCart;
import com.example.ekirana.SliderAdapter;
import com.example.ekirana.ViewPagerAdapter;
import com.example.ekirana.ViewPagerAdapterProducts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends AppCompatActivity {
    private ProductViewModel productViewModel;
    ImageView mImageView;
    ViewPager viewPager;
    private Retrofit retrofit;
    RecyclerView mRecyclerView;
    HorizontalRVAdapter adapter;
    private RequestQueue mQueue;
    List<ApiResponce> mList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        mRecyclerView = findViewById(R.id.slider_recyclerView);

        mList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(this);
       fetchProduct();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.products_acivity, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //fetchProduct();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.shopping_cart:
                Intent intent = new Intent(HomeFragment.this, ShoppingCart.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchProduct()
    {
        String url = "http://192.168.43.239/E-Kirana/getAllProducts.php";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("mydata");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                List<ProductsApiResponse> productList = new ArrayList<>();
                                ApiResponce item = new ApiResponce();
                                JSONObject product = jsonArray.getJSONObject(i);
                                item.setCategory(product.getString("category"));
                                JSONArray array = product.getJSONArray("data");
                                for (int j=0;j<array.length();j++)
                                {
                                    JSONObject object = array.getJSONObject(j);
                                    Log.d("TAG", "onResponse: "+object.getString("name"));
                                    productList.add(new ProductsApiResponse(Integer.parseInt(object.getString("id")),
                                            object.getString("name"),object.getString("description"),Integer.parseInt(object.getString("price")),
                                            object.getString("image_url"),object.getString("msg"),object.getString("category")
                                    ));
                                }

                                item.setProductsList(productList);
                                mList.add(item);
                            }
                            generateDataList(mList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void generateDataList(List<ApiResponce> photoList) {
        Toast.makeText(this, "generateDataList", Toast.LENGTH_SHORT).show();

        adapter = new HorizontalRVAdapter(this,photoList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

    }


}
