package com.enredados.petit.GUI;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class IngresoPacienteActivity extends AppCompatActivity {

    private Spinner especieSpinner, generoSpinner;
    private EditText etCodigo, etNombre, etRaza, etPeso, etEdad, etCedula;
    private String[] info = new String[0];
    private boolean editar = false;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_paciente);
        etCodigo = findViewById(R.id.codigoEditText);
        etNombre = findViewById(R.id.nombreEditText);
        etRaza = findViewById(R.id.razaEditText);
        etPeso = findViewById(R.id.pesoEditText);
        etEdad = findViewById(R.id.edadEditText);
        etCedula = findViewById(R.id.cedulaEditText);
        especieSpinner = findViewById(R.id.especieSpinner);
        generoSpinner = findViewById(R.id.generoSpinner);

        //Opciones del spinner
        String[] especie = {"Perro", "Gato", "Exótico"};
        String[] genero = {"Macho", "Hembra"};
        ArrayAdapter<String> especieAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, especie);
        especieSpinner.setAdapter(especieAdapter);
        ArrayAdapter<String> generoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);
        generoSpinner.setAdapter(generoAdapter);

        Intent men = getIntent();
        info = men.getStringArrayExtra(PerfilMascota.INFO);
        editar = info == null? false: true;
        if(editar){
            setTitle("Editar Mascota");
            etCodigo.setText(info[0]);
            etCodigo.setFocusable(false);
            etNombre.setText(info[1]);
            for(int i =0; i<3; i++){
                if(especie[i].equals(info[2])) {
                    especieSpinner.setSelection(i);
                    break;
                }
            }
            especieSpinner.setFocusable(false);
            etRaza.setText(info[3]);
            int i = info[4] == "Macho"? 0 : 1;
            generoSpinner.setSelection(i);
            generoSpinner.setFocusable(false);
            etPeso.setText(info[5]);
            etEdad.setText(info[6]);
            etCedula.setText(info[7]);
            etCedula.setFocusable(false);
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
        String cedula = etCedula.getText().toString();


        Map<String, Object> pacientes = new HashMap<>();
        pacientes.put("nombre", nombre);
        pacientes.put("especie", especie);
        pacientes.put("raza", raza);
        pacientes.put("genero", genero);
        pacientes.put("peso", peso);
        pacientes.put("edad", edad);
        pacientes.put("user", user.getEmail());
        pacientes.put("prop", cedula);

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
