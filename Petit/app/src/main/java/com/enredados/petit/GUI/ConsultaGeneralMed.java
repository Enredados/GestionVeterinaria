//BORRAR: BY JACK
package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ConsultaGeneralMed extends AppCompatActivity {

    FirebaseFirestore db;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_general_med);
        db = FirebaseFirestore.getInstance();
        listview = (ListView) findViewById(R.id.listviev);
        Button consultar = findViewById(R.id.ConsultaGeneralMed);
        consultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                consultaGeneral();
            }
        }

        );
    }
    private void consultaGeneral(){
        db.collection("MEDICAMENTO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> names = new ArrayList<String>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                names.add(document.get("nom").toString() + ", " + document.get("stock").toString());
                            }
                            mandar(names);
                        } else {
                        }
                    }
                });
    }

    private void mandar(ArrayList<String> names ){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(arrayAdapter);
    }

}