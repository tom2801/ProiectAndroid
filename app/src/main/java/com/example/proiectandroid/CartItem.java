package com.example.proiectandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class CartItem { // voi folosi tehnica de select cu returnare sub forma de multimap
    @PrimaryKey(autoGenerate = true)
    public int id_cart_item;

    @NotNull
    public int id_user;

    @NotNull
    public int id_product;
}
