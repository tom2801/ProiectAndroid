package com.example.proiectandroid;

import android.os.AsyncTask;

public class InsertUserOperation extends AsyncTask<User,Void,String> {
    UserOperationsListener listener;

    public InsertUserOperation (UserOperationsListener listener){
        this.listener=listener;
    }

    @Override
    protected String doInBackground(User... users){
        try{
            ApplicationController.getAppDatabase().userDao().insertAllUsers(users);
        } catch (Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertUsersResponse(result);
    }


}
