package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekirana.ui.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mpass;
    TextView msignUp_text;
    private Gson gson;
    private Retrofit retrofit;
    SharedPreferences sharedPreferences;
    private int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mpass = findViewById(R.id.password);
        msignUp_text = findViewById(R.id.signup_text);
        msignUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.238/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public void checkLogin(View view)
    {
        Log.d("TAG", "checkLogin: ");

        Api api = retrofit.create(Api.class);

        Call<Responce> call = api.checkLogin(mEmail.getText().toString(),mpass.getText().toString());
        call.enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                if (response.isSuccessful())
                {
                    response.body();
                     id = response.body().getID();
                    Log.d("RetroCall", "onResponse: "+ id);
                    if(id>0)
                    {
                        loginSuccessful();
                    }
                    else
                    {
                        Log.d("TAG", "onResponse:  INvalid");
                        loginUnsuccessful();
                    }
                }
                else
                {
                    Log.d("TAG", "onResponse:  else");
                    loginUnsuccessful();

                }
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {

            }
        });
    }

    public void signUP(View view)
    {
        Toast.makeText(this, "Sign Up", Toast.LENGTH_SHORT).show();
    }

    public void loginSuccessful()
    {
        sharedPreferences = getSharedPreferences(GlobalConstants.LOGIN_VALIDATION,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserID",id);
        editor.apply();
        editor.commit();
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }

    public void loginUnsuccessful()
    {
        Log.d("TAG", "loginUnsuccessful: ");
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Login Info")
                .setMessage("Invalid Credentials")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();

    }
}
