package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.enredados.petit.R;
import com.enredados.petit.complementos.Modelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class VisorDescripcion extends AppCompatActivity {

    FirebaseFirestore db;
    String codigoPaciente;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_descripcion);

        db = FirebaseFirestore.getInstance();

        TextView tipo = (TextView) findViewById(R.id.tvTituloTipo);
        TextView fecha = (TextView) findViewById(R.id.tvTituloFecha);
        TextView descripcion = (TextView) findViewById(R.id.tvDescripcion);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b != null){
            tipo.setText(b.getString("tipo"));
            fecha.setText(b.getString("fecha"));
            descripcion.setText(b.getString("descripcion"));
            codigoPaciente = b.getString("codigoPaciente");
        }

        // guardar la descripcion de la cita
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevaDescripcion = descripcion.getText().toString();
                guardarDescripcion(nuevaDescripcion, codigoPaciente);
            }
        });
    }

    public void guardarDescripcion(String descripcion, String codigoPaciente){
        Map<String, Object> update = new HashMap<>();
        update.put("descripcion", descripcion);

        db.collection("CITA").document("perro3").update(update)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showSucces();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        showAlert();
                    }
                });

    }

    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRO");
        builder.setMessage("Se ha ingresado el medicamento de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido ingresar el medicamento ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}