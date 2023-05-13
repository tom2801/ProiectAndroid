package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class SelectUserOperation extends AsyncTask<String,Void,User> {

    UserOperationsListener listener;

    public SelectUserOperation (UserOperationsListener listener){
        this.listener=listener;
    }

    @Override
    protected User doInBackground(String... credentials){
        String userName = credentials[0];
        String password = credentials[1];
        try{
           return ApplicationController.getAppDatabase().userDao().getUserByCredentials(userName,password).get(0);
        } catch (Exception e){
            return null;
        }

    }

    @Override
    protected void onPostExecute(User result){
        listener.selectUserResponse(result);
    }
}
