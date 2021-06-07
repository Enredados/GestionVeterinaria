package com.enredados.petit.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.DP.PacienteDP;
import com.enredados.petit.R;
import com.enredados.petit.databinding.ActivityPacienteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PacienteActivity extends AppCompatActivity {
    ListView listaMascotas;
    ArrayList<PacienteDP> pacientes = new ArrayList<PacienteDP>();
    private FirebaseFirestore db;
    final static String ACT_INFO = "com.enredados.petit.GUI.IngresoPacienteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        listaMascotas = (ListView) findViewById(R.id.listaMascotas);
        mostrarListaPacientes();

        listaMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent registro = new Intent(view.getContext(), IngresoPacienteActivity.class);
                //System.out.println(position);
                PacienteDP paciente = pacientes.get(position);
                String[] info = new String[7];

                info[0] = paciente.getCodigo();
                info[1] = paciente.getNombre();
                info[2] = paciente.getEspecie();
                info[3] = paciente.getRaza();
                info[4] = paciente.getGenero();
                info[5] = String.valueOf(paciente.getPeso());
                info[6] = String.valueOf(paciente.getEdad());

                registro.putExtra(ACT_INFO, info);
                startActivity(registro);
            }
        });

    }
    private void mostrarListaPacientes() {
        db = FirebaseFirestore.getInstance();
        db.collection("PACIENTE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> nombre = new ArrayList<String>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PacienteDP paciente = new PacienteDP();
                                nombre.add(document.get("nombre").toString());
                                paciente.setCodigo(document.getId().toString());
                                paciente.setNombre(document.get("nombre").toString());
                                paciente.setEspecie(document.get("especie").toString());
                                paciente.setRaza(document.get("raza").toString());
                                paciente.setGenero(document.get("genero").toString());
                                paciente.setPeso(Double.parseDouble(document.get("peso").toString()));
                                paciente.setEdad(Integer.parseInt(document.get("edad").toString()));
                                pacientes.add(paciente);
                            }
                            setLista(nombre);
                        } else {
                        }
                    }
                });
    }
    private void setLista(ArrayList<String> nombre) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombre);
        listaMascotas.setAdapter(arrayAdapter);
    }

    public void abrirRegistro(View vista) {
        Intent registro = new Intent(this, IngresoPacienteActivity.class);
        String[] info = new String[1];

        registro.putExtra(ACT_INFO, info);
        startActivity(registro);
    }

    protected void onResume(Bundle savedInstanceState) {
        mostrarListaPacientes();

    }
}
