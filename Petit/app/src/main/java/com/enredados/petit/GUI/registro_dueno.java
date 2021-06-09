package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.R;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class registro_dueno extends AppCompatActivity {
    private EditText etCedula, etNombre, etApellido, etDomicilio, etCiudad, etCelular;
    private String[] info;
    private FloatingActionButton btnEliminar;

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
        btnEliminar = findViewById(R.id.floatingActionButton5);
        btnEliminar.setVisibility(View.INVISIBLE);

        Intent men = getIntent();
        info = men.getStringArrayExtra(dueno.ACT_INFO);


        if (info.length > 1) {
            setTitle("Editar Cliente");
            etCedula.setVisibility(View.INVISIBLE);
            btnEliminar.setVisibility(View.VISIBLE);
            etCedula.setText(info[0]);
            etNombre.setText(info[1]);
            etApellido.setText(info[2]);
            etDomicilio.setText(info[3]);
            etCiudad.setText(info[4]);
            etCelular.setText(info[5]);
        }

    }

    public void crearDueno(View view) {

        String cedula = etCedula.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String domicilio = etDomicilio.getText().toString();
        String ciudad = etCiudad.getText().toString();
        String celular = etCelular.getText().toString();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        Map<String, Object> duenos = new HashMap<>();
        duenos.put("nombre", nombre);
        duenos.put("apellido", apellido);
        duenos.put("domicilio", domicilio);
        duenos.put("ciudad", ciudad);
        duenos.put("celular", celular);
        duenos.put("email", email);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("PROPIETARIO").document(cedula)
                .set(duenos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showLista();
                        toastAgregado();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        toastError();
                    }
                });
    }

    private void toastAgregado(){
        Toast.makeText(this, "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }
    private void toastEliminado(){
        Toast.makeText(this, "Se eliminaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }
    private void toastError(){
        Toast.makeText(this, "Se produjo un error",
                Toast.LENGTH_SHORT).show();
    }

    public void eliminar(View view){
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("PROPIETARIO").document(etCedula.getText().toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showLista();
                        toastEliminado();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });
    }

    public void showLista(){
        Intent lista = new Intent(this, dueno.class);
        startActivity(lista);
    }
}