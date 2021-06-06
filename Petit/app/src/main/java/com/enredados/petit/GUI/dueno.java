package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.MD.DuenoMD;
import com.enredados.petit.R;

import java.util.ArrayList;

public class dueno extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dueno);

        listview = (ListView) findViewById(R.id.listviev);
        DuenoDP duenoDP = new DuenoDP();
        ArrayList<DuenoDP> duenos = duenoDP.ConsultaGeneral();
        ArrayList<String> nombres = new ArrayList<String>();

        if(duenos.size()>0){
            for(DuenoDP dueno : duenos){
                nombres.add(dueno.getApellido()+", "+dueno.getNombre());
            }
        }else{

        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nombres);

        listview.setAdapter(arrayAdapter);
    }

    public void abrirOtro(View vista){
        Intent registro = new Intent(this, registro_dueno.class);
        startActivity(registro);
    }
}