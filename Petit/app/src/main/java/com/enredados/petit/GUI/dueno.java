package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.enredados.petit.R;

public class dueno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dueno);
    }

    public void abrirOtro(View vista){
        Intent registro = new Intent(this, registro_dueno.class);
        startActivity(registro);

    }
}