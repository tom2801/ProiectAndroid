package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAllUsers(User... users);

    @Query("SELECT * FROM user WHERE userName =:userName AND password = :password")
    List<User> getUserByCredentials(String userName,String password);
}
