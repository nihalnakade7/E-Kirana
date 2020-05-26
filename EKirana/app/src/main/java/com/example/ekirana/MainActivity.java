package com.example.ekirana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ekirana.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(GlobalConstants.LOGIN_VALIDATION,MODE_PRIVATE);
        GetLoginDetails obj = new GetLoginDetails();
        obj.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetLoginDetails obj = new GetLoginDetails();
        obj.execute();
    }

    private class GetLoginDetails extends AsyncTask<Void,Void,Integer>
    {


        @Override
        protected Integer doInBackground(Void... voids) {

            id = sharedPreferences.getInt("UserID",0);
            return id;
        }

        @Override
        protected void onPostExecute(Integer id) {
            if(id>0)
            {
                alreadyLogin();
            }
            else
            {
                sendToLoginActivity();
            }
        }
    }

    public void alreadyLogin()
    {
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }

    public void sendToLoginActivity()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
