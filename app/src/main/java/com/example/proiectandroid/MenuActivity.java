package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.idUserLogat;
import static com.example.proiectandroid.MainActivity.userNameLogat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity implements ProductOperationsListener {

    private int idUserLoggedIn;

    private String userNameLoggedIn;

    private Fragment navFragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idUserLoggedIn = bundle.getInt(idUserLogat);
        userNameLoggedIn = bundle.getString(userNameLogat);

        Bundle bundleFragment = new Bundle();
        bundleFragment.putString("userName",userNameLoggedIn);
        bundleFragment.putInt("userId",idUserLoggedIn);

        navFragment1= new NavFragment();

        navFragment1.setArguments(bundleFragment);

        Toast.makeText(this, userNameLoggedIn, Toast.LENGTH_SHORT).show();
           if(savedInstanceState == null){
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id. nav_fragment_container , navFragment1)
                       .commit() ;
           }



    }


    @Override
    public void insertProductsResponse(String result) {

    }

    @Override
    public void selectProductsResponse(List<Product> result) {

    }
}