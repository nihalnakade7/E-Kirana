package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Gson gson;
    private Retrofit retrofit;
    EditText mReg_email;
    EditText mReg_pass;
    EditText mReg_number;
    EditText mReg_address;
    EditText mReg_Name;
    TextView mLogin_text;
    private String apiResponse;

    private String mEmail,mPass,mNumber,mAddress,mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mReg_email = findViewById(R.id.reg_email);
        mReg_pass = findViewById(R.id.reg_password);
        mReg_number = findViewById(R.id.number);
        mReg_address = findViewById(R.id.address);
        mReg_Name = findViewById(R.id.name);
        mLogin_text = findViewById(R.id.loginText);
        mLogin_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
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

    public void registerUser(View view)
    {
        mName = mReg_Name.getText().toString();
        mAddress = mReg_address.getText().toString();
        mNumber = mReg_number.getText().toString();
        mEmail = mReg_email.getText().toString();
        mPass = mReg_pass.getText().toString();

        if(!mName.isEmpty() && !mAddress.isEmpty() && !mNumber.isEmpty() && !mEmail.isEmpty() && !mPass.isEmpty())
        {
            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            Api api = retrofit.create(Api.class);

            Call<RegistrationResponse> call = api.registerUser(mName,mAddress,mNumber,mEmail,mPass);
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    response.body();
                    if (response.isSuccessful())
                    {
                        Boolean success = response.body().getSuccess();
                        int id = response.body().getId();
                        apiResponse = response.body().getMsg();
                        if (id > 0)
                        {
                            Log.d("TAG", "onResponse: "+ success);
//                            registrationSuccessful();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }else {
                            registrationFailed();
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                }
            });
        }
        else
        {
            Toast.makeText(this, "Please Enter the Fields", Toast.LENGTH_SHORT).show();
        }
    }




    private void registrationSuccessful()
    {

    }

    private void registrationFailed()
    {
        Toast.makeText(this, "Registration Not Succeeded... Please Try Again!  :)", Toast.LENGTH_SHORT).show();
    }
}
