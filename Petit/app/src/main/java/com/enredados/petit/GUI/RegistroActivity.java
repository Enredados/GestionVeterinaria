package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private EditText nombre;
    private EditText apellido;
    private EditText cedula;
    private EditText email;
    private EditText clave;

    private Button registro;
    private Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        db = FirebaseFirestore.getInstance();
        nombre = findViewById(R.id.authLayout);
        apellido = findViewById(R.id.apellidoEditText);
        cedula = findViewById(R.id.cedulaEditText);
        email = findViewById(R.id.emailEditText);
        clave = findViewById(R.id.passwordEditText);

        registro = findViewById(R.id.registrar);
        consultar = findViewById(R.id.consulta);
        //registro
        registro();
    }

    public void registro() {

        setTitle("REGISTRO");

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("nombre", nombre.getText().toString());
                user.put("apellido", apellido.getText().toString());
                user.put("id", cedula.getText().toString());
                db.collection("VETERINARIO").document(email.getText().toString()).set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {

                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), clave.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    showHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                                                } else {
                                                    showAlert();
                                                }
                                            }
                                        });

                                showSucces();
                            }
                        });
            }
        });


    }

    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRO");
        builder.setMessage("Se ha creado la cuenta de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido registrar la cuenta");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHome(String email, ProviderType provider) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("email", email);
        homeIntent.putExtra("provider", provider);
        startActivity(homeIntent);
    }

}