package com.example.proiectandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id_user;

    @NotNull
    public String userName;

    @NotNull
    public String password;

    // de adaugat eventual optiunea de poza

    public User (int id_user, @NotNull String userName, @NotNull String password){
        this.id_user=id_user;
        this.userName=userName;
        this.password=password;
    }

}
