package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.enredados.petit.DP.CitaDP;
import com.enredados.petit.R;
import com.enredados.petit.complementos.Modelo;

import java.util.List;


public class HistorialCitasActivity extends AppCompatActivity {

    // atributos
    private ListView listViewCitas;
    private List<CitaDP> objetosCitas;
    private List<Modelo> listaCitas;
    private ArrayAdapter adapter;
    private CitaDP citaDP;
    ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas);

        // reconocer el componente listview
        listViewCitas = findViewById(R.id.lista);
        //objetosCitas = citaDP.consultarHistorialCitas();

        listaCitas.add(new Modelo("tipo1", null));
        mAdapter = new com.enredados.petit.complementos.ListAdapter(this, R.layout.item_row, listaCitas);
        listViewCitas.setAdapter(mAdapter);
    }













/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas);

        // reconocer el componente listview
        listViewCitas = findViewById(R.id.lista);
        objetosCitas = citaDP.consultarHistorialCitas();
        cargarEnLista();
        mostrarLista();
    }

    public void cargarEnLista(){
        for(CitaDP temp : objetosCitas){
            listaCitas.add(temp.getDescripcion());
        }
    }

    public void mostrarLista(){
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCitas);
        listViewCitas.setAdapter(adapter);
    }
    */



}