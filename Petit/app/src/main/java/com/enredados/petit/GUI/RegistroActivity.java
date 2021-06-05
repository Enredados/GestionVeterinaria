package com.enredados.petit.GUI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enredados.petit.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //registro
        registro();
    }

    public void registro()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText nombre =  findViewById(R.id.nombreEditText);
        EditText apellido = findViewById(R.id.apellidoEditText);
        EditText cedula = findViewById(R.id.cedulaEditText);
        EditText email = findViewById(R.id.emailEditText);
        EditText clave = findViewById(R.id.passwordEditText);

        Button registro = findViewById(R.id.registrar);

        setTitle("REGISTRO");

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                db.collection("");

            }
        });

    }

    private void showAlert(String aux) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRO");
        builder.setMessage("Se ha creado la cuenta de manera exitosa " + aux);
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}