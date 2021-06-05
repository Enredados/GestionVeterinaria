package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.enredados.petit.R;

enum ProviderType{
    BASIC
}

public class HomeActivity extends AppCompatActivity {

    public HomeActivity() {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setup
    }
}