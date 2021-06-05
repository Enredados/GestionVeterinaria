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
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AuthActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString("message", "Integracion de firebase completa");
        mFirebaseAnalytics.logEvent("InitScreen", bundle);

        //setup
        setup();
    }

    public void setup() {
        EditText emailView = findViewById(R.id.nombreEditText);
        EditText passView = findViewById(R.id.passwordEditText);
        Button registrar = findViewById(R.id.signUpButton);
        Button ingresar = findViewById(R.id.logInButton);

        setTitle("Autenticación");
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistro();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailView.getText().toString().isEmpty() && !passView.getText().toString().isEmpty()) {
                    System.out.println(emailView.getText().toString() + " " + passView.getText().toString());

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailView.getText().toString(), passView.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        showHome(task.getResult().getUser().getEmail(),ProviderType.BASIC);
                                    }
                                    else
                                    {
                                        showAlert();
                                    }
                                }
                            });
                }
            }
        });
    }


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("Se ha encontrando un error en la autenticacion");
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

    private void showRegistro()
    {
        Intent registroIntent = new Intent(this, RegistroActivity.class);
        startActivity(registroIntent);
    }
}
