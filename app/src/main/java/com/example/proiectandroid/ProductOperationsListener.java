package com.example.proiectandroid;

import java.util.List;

public interface ProductOperationsListener {
    void insertProductsResponse(String result); // va fi suprascrisa in Activitati

    void selectProductsResponse(List<Product> result);
}
