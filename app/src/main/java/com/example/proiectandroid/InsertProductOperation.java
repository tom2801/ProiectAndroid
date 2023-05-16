package com.example.proiectandroid;

import android.os.AsyncTask;

public class InsertProductOperation extends AsyncTask<Product,Void,String> {
    ProductOperationsListener listener;

    public InsertProductOperation (ProductOperationsListener listener){
        this.listener=listener;
    }

    @Override
    protected String doInBackground(Product... products){
        try{
            ApplicationController.getAppDatabase().ProductDao().insertAllProducts(products);
        } catch (Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result){
        listener.insertProductsResponse(result);
    }


}
