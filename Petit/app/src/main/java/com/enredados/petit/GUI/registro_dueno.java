package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.R;

public class registro_dueno extends AppCompatActivity {
    private EditText etCedula, etNombre, etApellido, etDomicilio, etCiudad, etCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dueno);


        etCedula = (EditText) findViewById(R.id.Cedula);
        etNombre = (EditText) findViewById(R.id.Nombre);
        etApellido = (EditText) findViewById(R.id.Apellido);
        etDomicilio = (EditText) findViewById(R.id.Domicilio);
        etCiudad = (EditText) findViewById(R.id.Ciudad);
        etCelular = (EditText) findViewById(R.id.Celular);
    }

    public void crearDueno(View view){

        int i=1;
        String cedula = etCedula.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String domicilio = etDomicilio.getText().toString();
        String ciudad = etCiudad.getText().toString();
        String celular = etCelular.getText().toString();

        DuenoDP duenoDP = new DuenoDP(cedula, nombre, apellido, domicilio, ciudad, celular);
        duenoDP.guardarDueno();
    }
}