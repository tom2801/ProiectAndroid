package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.userNameLogat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavFragment extends Fragment {

    private TextView userNameLoggedIn;
    public NavFragment() {
        super(R.layout.fragment_nav);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(
                R.layout.fragment_nav, container, false);

        userNameLoggedIn = (TextView)view.findViewById(
                R.id.userNameLoggedIn);

        Bundle bundle = getArguments();

        if(bundle != null ){ // din motive de async, este nevoie de acest if
            String userName = bundle.getString("userName");
            userNameLoggedIn.setText(userName);
        }


        return view;
    }
}

