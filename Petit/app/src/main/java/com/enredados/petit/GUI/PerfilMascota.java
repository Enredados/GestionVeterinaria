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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;


public class PerfilMascota extends AppCompatActivity {
    private String codigoPaciente;
    private EditText nombre;
    private String[] info;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final static String INFO = "com.enredados.petit.GUI.IngresoPacienteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mascota);

        nombre = findViewById(R.id.nombreEText);

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

    public void btn_verFicha(View vista) {
        datosPaciente();
    }
    private void datosPaciente() {
        Intent ficha = new Intent(this, IngresoPacienteActivity.class);
        db.collection("PACIENTE").document(info[1]).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    PacienteDP paciente = new PacienteDP();
                    paciente.setCodigo(documentSnapshot.getId());
                    if(documentSnapshot.contains("nombre")){
                        paciente.setNombre(documentSnapshot.getString("nombre")); ;
                    }
                    if(documentSnapshot.contains("especie")){
                        paciente.setEspecie(documentSnapshot.getString("especie"));
                    }
                    if(documentSnapshot.contains("raza")){
                        paciente.setRaza( documentSnapshot.getString("raza"));
                    }
                    if(documentSnapshot.contains("genero")){
                        paciente.setGenero(documentSnapshot.getString("genero"));
                    }
                    if(documentSnapshot.contains("peso")){
                        paciente.setPeso(documentSnapshot.getString("peso"));
                    }
                    if(documentSnapshot.contains("edad")){
                        paciente.setEdad(documentSnapshot.getString("edad"));
                    }
                    if(documentSnapshot.contains("prop")){
                        paciente.setCedula(documentSnapshot.getString("prop"));
                    }
                    String[] info = new String[8];

                    info[0] = paciente.getCodigo();
                    info[1] = paciente.getNombre();
                    info[2] = paciente.getEspecie();
                    info[3] = paciente.getRaza();
                    info[4] = paciente.getGenero();
                    info[5] = paciente.getPeso();
                    info[6] = paciente.getEdad();
                    info[7] = paciente.getCedula();

                    ficha.putExtra(INFO, info);
                    startActivity(ficha);
                }
            }
        });
    }
    public void btn_agendar(View v){
        Intent agendar = new Intent(this, AgendarCita.class);
        startActivity(agendar);
    }
}