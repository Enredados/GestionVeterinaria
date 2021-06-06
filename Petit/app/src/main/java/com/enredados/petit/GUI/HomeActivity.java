package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.enredados.petit.R;

enum ProviderType{
    BASIC
}

public class HomeActivity extends AppCompatActivity {

    public HomeActivity() {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Setup
    }

    public void abrirDueno(View vista){
        Intent dueno = new Intent(this, dueno.class);
        startActivity(dueno);
    }

    public void abrirMedi(View vista){
        Intent medic = new Intent(this, IngresoMed.class);
        startActivity(medic);
    }
}