package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.idUserLogat;
import static com.example.proiectandroid.MainActivity.userNameLogat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MenuActivity extends AppCompatActivity implements ProductOperationsListener {

    private int idUserLoggedIn;

    private String userNameLoggedIn;

    private Fragment navFragment1;

    private ImageView signOutButton;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        Bundle bundleFragment = new Bundle();


        navFragment1= new NavFragment();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null && bundle.getBoolean("googleLogIn")){
            bundleFragment.putString("userName",acct.getDisplayName());
        }else {
            idUserLoggedIn = bundle.getInt(idUserLogat);
            userNameLoggedIn = bundle.getString(userNameLogat);
            bundleFragment.putString("userName",userNameLoggedIn);
            bundleFragment.putInt("userId",idUserLoggedIn);

        }

        navFragment1.setArguments(bundleFragment);
//        if (userNameLoggedIn != null) {
//
//            Toast.makeText(this, userNameLoggedIn, Toast.LENGTH_SHORT).show();
//        }

           if(savedInstanceState == null){
               getSupportFragmentManager().beginTransaction()
                       .replace(R.id. nav_fragment_container , navFragment1)
                       .commit() ;

               getSupportFragmentManager().beginTransaction()
                       .setReorderingAllowed(true)
                       .add(R.id.items_fragment_container,ItemsContainer.class, null)
                       .commit();
           }

        signOutButton = findViewById(R.id.LogOut);

         if(signOutButton!=null){
             signOutButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     signOut();
                 }
             });

         }

    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                    finish();
                    startActivity(new Intent(MenuActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void insertProductsResponse(String result) {

    }

    @Override
    public void selectProductsResponse(List<Product> result) {

    }
}