package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class dueno extends AppCompatActivity {
    ListView listview;
    ArrayList<DuenoDP> duenos = new ArrayList<DuenoDP>();
    private FirebaseFirestore db;
    final static String ACT_INFO = "com.enredados.petit.GUI.registro_dueno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dueno);
        listview = (ListView) findViewById(R.id.listviev);
        mostrarListaDuenos();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent registro = new Intent(view.getContext(), registro_dueno.class);
                System.out.println(position);
                DuenoDP dueno = duenos.get(position);
                String[] info = new String[6];

                info[0] = dueno.getCedula();
                info[1] = dueno.getNombre();
                info[2] = dueno.getApellido();
                info[3] = dueno.getDomicilio();
                info[4] = dueno.getCiudad();
                info[5] = dueno.getCelular();

                registro.putExtra(ACT_INFO, info);
                startActivity(registro);
            }
        });
    }

    private void mostrarListaDuenos() {
        db = FirebaseFirestore.getInstance();
        db.collection("PROPIETARIO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> names = new ArrayList<String>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DuenoDP dueno = new DuenoDP();
                                names.add(document.get("apellido").toString() + ", " + document.get("nombre").toString());
                                dueno.setCedula(document.getId().toString());
                                dueno.setNombre(document.get("nombre").toString());
                                dueno.setApellido(document.get("apellido").toString());
                                dueno.setDomicilio(document.get("domicilio").toString());
                                dueno.setCiudad(document.get("ciudad").toString());
                                dueno.setCelular(document.get("celular").toString());
                                dueno.setEmail(document.get("email").toString());
                                duenos.add(dueno);
                            }
                            setLista(names);
                        } else {
                        }
                    }
                });
    }

    private void setLista(ArrayList<String> names) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(arrayAdapter);
    }

    public void abrirRegistro(View vista) {
        Intent registro = new Intent(this, registro_dueno.class);
        String[] info = new String[1];

        registro.putExtra(ACT_INFO, info);
        startActivity(registro);
    }

    protected void onResume(Bundle savedInstanceState) {
        mostrarListaDuenos();

    }
}