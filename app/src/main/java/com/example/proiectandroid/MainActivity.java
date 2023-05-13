package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements UserOperationsListener{

    //private TextView textView;

    private Button registerButton;
    private AppCompatEditText registerUserName;
    private AppCompatEditText registerPassword;

    private Button loginButton;
    private AppCompatEditText loginUserName;
    private AppCompatEditText loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = findViewById(R.id.registerText);
        registerButton = findViewById(R.id.registerButton);
        registerUserName = findViewById(R.id.registerUserName);
        registerPassword = findViewById(R.id.registerPassword);

        loginButton = findViewById(R.id.loginButton);
        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);
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

                    userName = registerUserName.getText().toString();
                    password = registerPassword.getText().toString();
                    // de inserat functie de error popup
                    selectUserRequest(userName,password);
                }
            }
        });

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
        Toast.makeText(this, "username: "+result.userName, Toast.LENGTH_SHORT).show();
    }

    private void insertUserRequest(String userName, String password){
        User newUser = new User(userName,password);
        new InsertUserOperation(this).execute(newUser);
    }

    private void selectUserRequest(String userName, String password){
        String[] credentials ={userName,password};
        new SelectUserOperation(this ).execute(credentials);
    }
}