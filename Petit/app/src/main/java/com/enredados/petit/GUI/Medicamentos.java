package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.enredados.petit.DP.MedicamentoDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Medicamentos extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicamentos);
        setTitle("MODIFICAR MEDICAMENTOS");
        db = FirebaseFirestore.getInstance();
        listview = (ListView) findViewById(R.id.listviev);

        consultaGeneral();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent siguiente = new Intent(Medicamentos.this,ModificacionMed.class);
                String codigo = listview.getItemAtPosition(position).toString();
                String medicamento[] = codigo.split(" ");
                siguiente.putExtra("NombreMed",medicamento[0]);
                startActivity(siguiente);
            }
        });

    }
    private void consultaGeneral(){
        db.collection("MEDICAMENTO").whereEqualTo("user", usr.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> names = new ArrayList<String>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                names.add(document.getId()+"         "+document.get("nom").toString());
                            }
                            mandar(names);
                        } else {
                        }
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        consultaGeneral();
    }

    private void mandar(ArrayList<String> names ){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(arrayAdapter);
    }

    public void ventanaIngreso(View view){
        Intent siguiente = new Intent(this, IngresoMed.class);
        startActivity(siguiente);
    }



}