package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.enredados.petit.R;

import java.util.ArrayList;

public class dueno extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dueno);

        listview = (ListView) findViewById(R.id.listviev);

        ArrayList<String> prueba = new ArrayList<>();
        prueba.add("Android");
        prueba.add("IS");
        prueba.add("Great");
        prueba.add("AND");
        prueba.add("I");
        prueba.add("Am");
        prueba.add("Not");
        prueba.add("Gringo");
        prueba.add("A");
        prueba.add("Isaac");
        prueba.add("ZOrro");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,prueba);

        listview.setAdapter(arrayAdapter);
    }

    public void abrirOtro(View vista){
        Intent registro = new Intent(this, registro_dueno.class);
        startActivity(registro);
    }
}