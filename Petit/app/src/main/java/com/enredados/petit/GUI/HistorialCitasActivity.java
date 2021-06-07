package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.enredados.petit.DP.CitaDP;
import com.enredados.petit.R;
import com.enredados.petit.complementos.Adaptador;
import com.enredados.petit.complementos.Modelo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistorialCitasActivity extends AppCompatActivity {


    private ListView lvItems;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas);

        lvItems = (ListView) findViewById(R.id.lvItems);
        adaptador = new Adaptador(getArrayItems(), this);
        lvItems.setAdapter(adaptador);
    }

    private ArrayList<Modelo> getArrayItems(){
        ArrayList<Modelo> listItems = new ArrayList<>();
        Date now = new Date();
        String mensaje = "todo bien con el perro firulais, tiene cuatro patas y puede correr muy pero muy rapido. Además se le cortó el pelo y se le hizo un buen pedicure";

        listItems.add(new Modelo("Medica", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Peluqueria", "sin observaciones", DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Vacunacion", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Medica", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Vacunacion", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Operacion", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Medica", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Medica", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));
        listItems.add(new Modelo("Peluqueria", mensaje, DateFormat.getTimeInstance(DateFormat.SHORT).format(now)));

        return listItems;
    }



    /*
    // atributos
    private ListView listViewCitas;
    private List<CitaDP> objetosCitas;
    private List<Modelo> listaCitas;
    private ArrayAdapter adapter;
    private CitaDP citaDP;
    ListAdapter mAdapter;
    ListAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas);

        // reconocer el componente listview
        listViewCitas = findViewById(R.id.lista);
        //objetosCitas = citaDP.consultarHistorialCitas();


        // aqui cargar todas las citas de la db y crear objetos Modelo para presentar solo el tipo y la fecha
        listaCitas.add(new Modelo("tipo1", null));
        
        mAdapter = new com.enredados.petit.complementos.ListAdapter(this, R.layout.item_row, listaCitas);
        listViewCitas.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Elemento presionado: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HistorialCitasDescripcionActivity.class);
        // aqui cambiar el segundo argumento por mAdapter.getItem(position).getDescripcion()

        intent.putExtra("descripcion", mAdapter.getItem(position).getClass().toString());
        startActivity(intent);
    }
    */
}