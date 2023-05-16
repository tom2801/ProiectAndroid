package com.example.proiectandroid;

public interface ProductOperationsListener {
    void insertProductsResponse(String result); // va fi suprascrisa in Activitati

    void selectProductsResponse(Product result);
}
