package com.example.proiectandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Product {

    @PrimaryKey
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

    public static Product[] populateData() {
        return new Product[] {
                new Product(1, "Pizza1", 22),
                new Product(2, "Pizza2", 23),
                new Product(3, "Pizza3", 24),
                new Product(4, "Pizza4", 25),
                new Product(5, "Pizza5", 26)
        };
    }
}
