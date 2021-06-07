package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enredados.petit.DP.MedicamentoDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class EliminarMed extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_med);
        db = FirebaseFirestore.getInstance();
        Button ver = findViewById(R.id.btnVer);
        Button eliminar = findViewById(R.id.btnEliminar);
        EditText cod = findViewById(R.id.txtCodigo);
        setTitle("ELIMINAR MEDICAMENTO");
        ver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String codigo = cod.getText().toString();
                consultaParametro(codigo);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = cod.getText().toString();
                eliminar(codigo);
            }
        });
    }
    private void consultaParametro(String cod) {

        db.collection("MEDICAMENTO").document(cod).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    MedicamentoDP medicamento = new MedicamentoDP();

                    if(documentSnapshot.contains("nom")){
                        medicamento.setNomMed(documentSnapshot.getString("nom")) ;
                    }
                    if(documentSnapshot.contains("stock")){
                        medicamento.setStockMed(documentSnapshot.getDouble("stock"));
                    }
                    if(documentSnapshot.contains("tipo")){
                        medicamento.setTipoMed( documentSnapshot.getString("tipo"));
                    }
                    if(documentSnapshot.contains("user")){
                        medicamento.setUser(documentSnapshot.getString("user"));
                    }
                    TextView user = findViewById(R.id.txtUsuario);
                    TextView tipo = findViewById(R.id.txtTipo);
                    TextView nombre = findViewById(R.id.txtNombre);
                    TextView stock = findViewById(R.id.txtStock);
                    user.setText(medicamento.getUser());
                    tipo.setText(medicamento.getTipoMed());
                    nombre.setText(medicamento.getNomMed());
                    stock.setText(""+medicamento.getStockMed());
                }
            }
        });
    }

    private void eliminar(String cod){
        db.collection("MEDICAMENTO").document(cod)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showSucces();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        showAlert();
                    }
                });
    }

    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ELIMINADO");
        builder.setMessage("Se ha eliminado el medicamento de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido eliminar el medicamento");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}