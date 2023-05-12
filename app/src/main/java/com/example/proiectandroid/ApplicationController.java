package com.example.proiectandroid;

import android.app.Application;
import android.provider.SyncStateContract;

import androidx.room.Room;

public class ApplicationController extends Application {

    public static ApplicationController mInstance;

    public static AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance=this;
        mAppDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"baza_de_date").build();

       

    }
}