package com.example.proiectandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Product.class, CartItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProductDao ProductDao();
    public abstract CartItemDao CartItemDao();
}
