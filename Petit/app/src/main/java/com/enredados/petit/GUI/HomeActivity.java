package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.enredados.petit.R;
import com.google.firebase.auth.FirebaseAuth;

enum ProviderType {
    BASIC,
    GOOGLE
}

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences.Editor pref;

    public HomeActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setup
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        Object provider = bundle.get("provider");

        //Guardado de datos
        pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        pref.putString("email", email);
        pref.putString("provider", provider.toString());
        pref.apply();
    }

    public void abrirDueno(View vista) {
        Intent dueno = new Intent(this, dueno.class);
        startActivity(dueno);
    }

    public void abrirMedi(View vista) {
        Intent medic = new Intent(this, Medicamentos.class);
        startActivity(medic);
    }

    public void abrirPaciente(View vista) {
        Intent paciente = new Intent(this, PacienteActivity.class);
        startActivity(paciente);
    }

    public void logOut(View view) {
        pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        pref.clear();
        pref.apply();

        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }

}