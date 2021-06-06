package com.enredados.petit.GUI;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.enredados.petit.DP.PacienteDP;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.R;

public class IngresoPacienteActivity extends AppCompatActivity {

    private Spinner especieSpinner;
    private Spinner generoSpinner;
    private EditText codigo, nombre, raza, peso, edad;
    private PacienteDP pacienteDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_paciente);
        ingreso();
    }
    public void ingreso(){
        codigo = (EditText) findViewById(R.id.codigoEditText);
        nombre = (EditText) findViewById(R.id.authLayout);
        raza = (EditText) findViewById(R.id.razaEditText);
        peso = (EditText) findViewById(R.id.pesoEditText);
        edad = (EditText) findViewById(R.id.edadEditText);
        especieSpinner = (Spinner) findViewById(R.id.especieSpinner);
        generoSpinner = (Spinner) findViewById(R.id.generoSpinner);
        //Opciones del spinner
        String[] especie = {"Perro", "Gato", "Exótico"};
        String[] genero = {"Macho", "Hembra"};
        ArrayAdapter<String> especieAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, especie);
        especieSpinner.setAdapter(especieAdapter);
        ArrayAdapter<String> generoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);
        generoSpinner.setAdapter(generoAdapter);

        //Se carga el objeto PacienteDP
        pacienteDP = new PacienteDP();
        pacienteDP.setCodigo(codigo.toString());
        pacienteDP.setNombre(nombre.toString());
        pacienteDP.setEspecie(especieSpinner.getSelectedItem().toString());
        pacienteDP.setRaza(raza.toString());
        pacienteDP.setGenero(generoSpinner.getSelectedItem().toString());
        pacienteDP.setPeso(Double.parseDouble(peso.toString()));
        pacienteDP.setEdad(Integer.parseInt(edad.toString()));

        Button guardar = findViewById(R.id.guardarButton);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pacienteDP.insertarDP();
            }
        });
    }

}
