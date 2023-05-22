package com.example.proiectandroid;

import static com.example.proiectandroid.ItemsContainer.looped;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserOperationsListener, ProductOperationsListener{

    public static final String idUserLogat = "idUserLogat";

    public static final String userNameLogat = "userNameLogat";
    private TextView textViewRegister;

    private Button registerButton;
    private AppCompatEditText registerUserName;
    private AppCompatEditText registerPassword;

    private TextView textViewLogin;
    private Button loginButton;
    private AppCompatEditText loginUserName;
    private AppCompatEditText loginPassword;

    private Button toggleButton;

    private boolean  isLoginVisible;

    private boolean isProductListEmpty;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc=GoogleSignIn.getClient(this,gso);
        googleButton = findViewById(R.id.google_button);

        textViewRegister = findViewById(R.id.registerText);
        registerButton = findViewById(R.id.registerButton);
        registerUserName = findViewById(R.id.registerUserName);
        registerPassword = findViewById(R.id.registerPassword);

        textViewLogin = findViewById(R.id.loginText);
        loginButton = findViewById(R.id.loginButton);
        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);

        toggleButton = findViewById(R.id.toggleButton);

        isLoginVisible = true;
        isProductListEmpty = false;

        selectProductsRequest();


        googleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    googleSignIn();
                }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName= "";
                String password="";

                if(registerUserName.getText() != null && registerPassword.getText() != null){

                    userName = registerUserName.getText().toString();
                    password = registerPassword.getText().toString();
                    // de inserat functie de error popup
                    insertUserRequest(userName,password);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName= "";
                String password="";

                if(loginUserName.getText() != null && loginPassword.getText() != null){

                    userName = loginUserName.getText().toString();
                    password = loginPassword.getText().toString();
                    // de inserat functie de error popup
                    selectUserRequest(userName,password);
                }
            }
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginVisible){
                    registerButton.setVisibility(View.VISIBLE);
                    registerUserName.setVisibility(View.VISIBLE);
                    registerPassword.setVisibility(View.VISIBLE);
                    textViewRegister.setVisibility(View.VISIBLE);

                    loginButton.setVisibility(View.GONE);
                    loginUserName.setVisibility(View.GONE);
                    loginPassword.setVisibility(View.GONE);
                    textViewLogin.setVisibility(View.GONE);

                    isLoginVisible = false ;
                }else{
                    registerButton.setVisibility(View.GONE);
                    registerUserName.setVisibility(View.GONE);
                    registerPassword.setVisibility(View.GONE);
                    textViewRegister.setVisibility(View.GONE);

                    loginButton.setVisibility(View.VISIBLE);
                    loginUserName.setVisibility(View.VISIBLE);
                    loginPassword.setVisibility(View.VISIBLE);
                    textViewLogin.setVisibility(View.VISIBLE);

                    isLoginVisible = true ;
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        looped=false;
    }

    @Override
    public void insertUsersResponse(String result){
        if(result.equals("success")){
            Toast.makeText(this, "User creat cu succes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Userul nu a putut fi creat", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void selectUserResponse(User result){
        if(result  == null){
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(this, "username: "+result.userName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MenuActivity.class);
            Bundle mBundle = new Bundle() ;

            mBundle.putInt(idUserLogat,result.id_user);
            mBundle.putString(userNameLogat,result.userName);
            intent.putExtras(mBundle) ;
            startActivity(intent,mBundle);
        }

    }

    private void insertUserRequest(String userName, String password){
        User newUser = new User(userName,password);
        new InsertUserOperation(this).execute(newUser);
    }

    private void selectUserRequest(String userName, String password){
        String[] credentials ={userName,password};
        new SelectUserOperation(this ).execute(credentials);
    }

    private void insertInitialProducts(){
        Product[] produse={
            new Product(1,"pizza1",22),
            new Product(2,"pizza2",23),
            new Product(3,"pizza3",24),
            new Product(4,"pizza4",25)
        };
        new InsertProductOperation(this).execute(produse);
    }

    public void selectProductsRequest(){
        new SelectProductsOperation(this).execute();
    }

    @Override
    public void insertProductsResponse(String result){
        if(result.equals("success")){
            Toast.makeText(this, "Produsele au fost inserate", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void selectProductsResponse(List<Product> result){
        if(result == null || result.isEmpty()){
            isProductListEmpty=true;
        }
        if(isProductListEmpty){
            insertInitialProducts();
        }
    }

    void googleSignIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                task.getResult(ApiException.class);
                goToMenu();
            }catch(ApiException e){
                Toast.makeText(getApplicationContext(), "Eroare sign In", Toast.LENGTH_SHORT).show();
            }

        }
    }

    void goToMenu(){
        finish();
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}