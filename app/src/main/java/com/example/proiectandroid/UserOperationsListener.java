package com.example.proiectandroid;

public interface UserOperationsListener {
    void insertUsersResponse(String result); // va fi suprascrisa in Activitati

    void selectUserResponse(User result);
}
