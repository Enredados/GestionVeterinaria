package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    public void registro() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText nombre = findViewById(R.id.nombreEditText);
        EditText apellido = findViewById(R.id.apellidoEditText);
        EditText cedula = findViewById(R.id.cedulaEditText);
        EditText email = findViewById(R.id.emailEditText);
        EditText clave = findViewById(R.id.passwordEditText);

        Button registro = findViewById(R.id.registrar);
        Button consultar = findViewById(R.id.consulta);


        setTitle("REGISTRO");

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> user = new HashMap<>();
                user.put("nombre", nombre.getText().toString());
                user.put("apellido", apellido.getText().toString());

                db.collection("VETERINARIO").document(cedula.getText().toString()).set(user);/*.addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {

                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                showSucces();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                            showAlert();
                    }
                });*/
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

            }
        });


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("VETERINARIO")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        nombre.setText(document.get("nombre").toString());
                                        apellido.setText(document.get("apellido").toString());
                                    }
                                } else {
                                    showAlert();
                                }
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