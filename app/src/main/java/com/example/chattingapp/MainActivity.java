package com.example.chattingapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void login(View view) {
        findViewById(R.id.login).setVisibility(View.GONE);
        findViewById(R.id.signup).setVisibility(View.GONE);
        FragmentManager fm = getSupportFragmentManager();
        LoginFragment login = new LoginFragment();
        fm.beginTransaction().add(R.id.container, login).commit();
    }


    public void signup(View view) {
        findViewById(R.id.login).setVisibility(View.GONE);
        findViewById(R.id.signup).setVisibility(View.GONE);
        FragmentManager fm = getSupportFragmentManager();
        signupp signup = new signupp();
        fm.beginTransaction().add(R.id.container, signup).commit();

    }



}


