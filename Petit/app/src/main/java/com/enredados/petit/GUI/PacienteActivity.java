package com.enredados.petit.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.R;
import com.enredados.petit.databinding.ActivityPacienteBinding;

public class PacienteActivity extends AppCompatActivity {

    ActivityPacienteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPacienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String nombre = intent.getStringExtra("nombre");
            String especie = intent.getStringExtra("especie");
            String raza = intent.getStringExtra("raza");
            String genero = intent.getStringExtra("genero");
            String peso = intent.getStringExtra("peso");
            String edad = intent.getStringExtra("edad");
            int imagenId = intent.getIntExtra("imagenId", R.drawable.baloo);

            binding.nombreProfile.setText(nombre);
            binding.especieProfile.setText(especie);
            binding.razaProfile.setText(raza);
            binding.generoProfile.setText(genero);
            binding.pesoProfile.setText(peso);
            binding.edadProfile.setText(edad);
            binding.profileImagen.setImageResource(imagenId);


        }

    }
}
