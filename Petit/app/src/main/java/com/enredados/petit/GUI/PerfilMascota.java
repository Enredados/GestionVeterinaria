package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enredados.petit.DP.PacienteDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PerfilMascota extends AppCompatActivity {
    private String codigoPaciente;
    private EditText nombre;
    private String[] info;
    ArrayList<PacienteDP> pacientes = new ArrayList<PacienteDP>();
    private FirebaseFirestore db;
    final static String INFO = "com.enredados.petit.GUI.IngresoPacienteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mascota);

        nombre = (EditText) findViewById(R.id.nombreEText);

        Intent men = getIntent();
        info = men.getStringArrayExtra(PacienteActivity.ACT_INFO);
        nombre.setText(info[0]);

        // obtener el codigo del paciente del perfil que se muestra
        codigoPaciente = men.getStringExtra("codigoPaciente");
    }

    // metodo que lanza la vista del historial de las citas
    public void verHistorialCitas(View view){

    }

    public void verHistoralCitas(View view) {
        Intent visorHistorial = new Intent(this, HistorialCitasActivity.class);
        visorHistorial.putExtra("codigoPaciente", codigoPaciente);
        startActivity(visorHistorial);
    }

    /*public void btn_verFicha(View vista) {
        Intent ficha = new Intent(this, IngresoPacienteActivity.class);
        datosPaciente();
        PacienteDP paciente = pacientes.get(0);
        String[] info = new String[7];

        info[0] = paciente.getCodigo();
        info[1] = paciente.getNombre();
        info[2] = paciente.getEspecie();
        info[3] = paciente.getRaza();
        info[4] = paciente.getGenero();
        info[5] = paciente.getPeso();
        info[6] = paciente.getEdad();

        ficha.putExtra(INFO, info);
        startActivity(ficha);
    }
    private void datosPaciente() {
        db = FirebaseFirestore.getInstance();
        db.collection("PACIENTE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PacienteDP paciente = new PacienteDP();
                                paciente.setCodigo(document.getId().toString());
                                paciente.setNombre(document.get("nombre").toString());
                                paciente.setEspecie(document.get("especie").toString());
                                paciente.setRaza(document.get("raza").toString());
                                paciente.setGenero(document.get("genero").toString());
                                paciente.setPeso(document.get("peso").toString());
                                paciente.setEdad(document.get("edad").toString());
                                pacientes.add(paciente);
                            }
                        }
                    }
                });
    }*/
}