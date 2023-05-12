package com.example.proiectandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int id_product;

    @NotNull
    public String productName;

    @NotNull
    public int price;

    // ingredientele produsului vor fi afisate live folosind un api

    public Product (int id_product, @NotNull String productName, @NotNull int price){
        this.id_product=id_product;
        this.productName=productName;
        this.price=price;
    }

}
