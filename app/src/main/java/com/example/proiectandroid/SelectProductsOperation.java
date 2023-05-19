package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class SelectProductsOperation extends AsyncTask<Void,Void,List<Product>> {

    ProductOperationsListener listener;

    public SelectProductsOperation (ProductOperationsListener listener){
        this.listener=listener;
    }

    @Override
    protected List<Product> doInBackground(Void... voids){
        try{
            return  ApplicationController.getAppDatabase().ProductDao().getAllProducts();
        } catch (Exception e){
            return null;
        }
    }



    @Override
    protected void onPostExecute(List<Product> result){
        listener.selectProductsResponse(result);
    }
}
