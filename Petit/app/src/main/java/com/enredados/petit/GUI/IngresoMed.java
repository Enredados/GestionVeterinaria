package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.enredados.petit.DP.MedicamentoDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class IngresoMed extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_med);
        db = FirebaseFirestore.getInstance();
        Button agregar = findViewById(R.id.AgregarMed);
        EditText cod = findViewById(R.id.CodigoMed);
        EditText tip = findViewById(R.id.TipoMed);
        EditText nom = findViewById(R.id.NombreMed);
        EditText st = findViewById(R.id.stockMed);
        setTitle("INGRESO MEDICAMENTOS");

        agregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usuario = usr.getEmail().toString();
                String codigo = cod.getText().toString();
                String tipo = tip.getText().toString();
                String nombre = nom.getText().toString();
                int stock = Integer.parseInt(st.getText().toString());

                MedicamentoDP medicamento = new MedicamentoDP(usuario, codigo, tipo, nombre, stock);
                insertar(medicamento);
            }
        });
    }

    public void ventanaConsultaParametro(View view){
        Intent siguiente = new Intent(this, ConsultaParametroMed.class);
        startActivity(siguiente);
    }
    public void ventanaConsultaGeneral(View view){
        Intent siguiente = new Intent(this, ConsultaGeneralMed.class);
        startActivity(siguiente);
    }
    public void ventanaEliminar(View view){
        Intent siguiente = new Intent(this, EliminarMed.class);
        startActivity(siguiente);
    }


    private void insertar(MedicamentoDP medicamento){

        Map<String, Object> medicamentos = new HashMap<>();
        medicamentos.put("user", medicamento.getUser());
        medicamentos.put("tipo", medicamento.getTipoMed());
        medicamentos.put("nom", medicamento.getNomMed());
        medicamentos.put("stock", medicamento.getStockMed());

        db.collection("MEDICAMENTO").document(medicamento.getCodMed()).set(medicamentos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showSucces();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showAlert();
                    }
                });
    }

    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRO");
        builder.setMessage("Se ha ingresado el medicamento de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido ingresar el medicamento ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}