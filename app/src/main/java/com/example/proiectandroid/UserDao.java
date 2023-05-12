package com.example.proiectandroid;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface UserDao {
    @Insert
    void insertAllUsers(User... users);

    @Query("SELECT * FROM user WHERE userName LIKE :userName AND password LIKE :password")
    List<User> getUserByCredentials(int userName,int password);
}
