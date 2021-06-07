package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.enredados.petit.R;

public class VisorDescripcion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_descripcion);

        TextView tipo = (TextView) findViewById(R.id.tvTituloTipo);
        TextView fecha = (TextView) findViewById(R.id.tvTituloFecha);
        TextView descripcion = (TextView) findViewById(R.id.tvDescripcion);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            tipo.setText(b.getString("tipo"));
            fecha.setText(b.getString("fecha"));
            descripcion.setText(b.getString("descripcion"));
        }
    }
}