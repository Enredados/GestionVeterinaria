package com.enredados.petit.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class IngresoPacienteActivity extends AppCompatActivity {

    private Spinner especieSpinner, generoSpinner;
    private EditText etCodigo, etNombre, etRaza, etPeso, etEdad;
    private String[] info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_paciente);
        etCodigo = (EditText) findViewById(R.id.codigoEditText);
        etNombre = (EditText) findViewById(R.id.nombreEditText);
        etRaza = (EditText) findViewById(R.id.razaEditText);
        etPeso = (EditText) findViewById(R.id.pesoEditText);
        etEdad = (EditText) findViewById(R.id.edadEditText);
        especieSpinner = (Spinner) findViewById(R.id.especieSpinner);
        generoSpinner = (Spinner) findViewById(R.id.generoSpinner);

        //Opciones del spinner
        String[] especie = {"Perro", "Gato", "Exótico"};
        String[] genero = {"Macho", "Hembra"};
        ArrayAdapter<String> especieAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, especie);
        especieSpinner.setAdapter(especieAdapter);
        ArrayAdapter<String> generoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);
        generoSpinner.setAdapter(generoAdapter);

        Intent men = getIntent();
        info = men.getStringArrayExtra(PerfilMascota.INFO);


        if (info.length > 1) {
            setTitle("Editar Mascota");
            etCodigo.setVisibility(View.INVISIBLE);
            especieSpinner.setVisibility(View.INVISIBLE);
            generoSpinner.setVisibility(View.INVISIBLE);
            etCodigo.setText(info[0]);
            etNombre.setText(info[1]);
            etRaza.setText(info[3]);
            etPeso.setText(info[5]);
            etEdad.setText(info[6]);
        }

    }
    public void ingresar(View v){
        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String especie = especieSpinner.getSelectedItem().toString();
        String raza = etRaza.getText().toString();
        String genero = generoSpinner.getSelectedItem().toString();
        String peso = etPeso.getText().toString();
        String edad = etEdad.getText().toString();

        Map<String, Object> pacientes = new HashMap<>();
        pacientes.put("chip", codigo);
        pacientes.put("nombre", nombre);
        pacientes.put("especie", especie);
        pacientes.put("raza", raza);
        pacientes.put("genero", genero);
        pacientes.put("peso", peso);
        pacientes.put("edad", edad);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("PACIENTE").document(codigo)
                .set(pacientes)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        toastAgregado();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        toastError();
                    }
                });
        Intent lista = new Intent(v.getContext(), PacienteActivity.class);
        startActivity(lista);
    }
    private void toastAgregado(){
        Toast.makeText(this, "Se cargaron los datos de la mascota",
                Toast.LENGTH_SHORT).show();
    }
    private void toastError(){
        Toast.makeText(this, "No se cargaron los datos de la mascota",
                Toast.LENGTH_SHORT).show();
    }

}
