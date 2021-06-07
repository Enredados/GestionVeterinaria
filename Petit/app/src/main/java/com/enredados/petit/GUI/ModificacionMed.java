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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ModificacionMed extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion_med);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            setTitle("MODIFICAR "+bundle.getString("NombreMed"));
        }

        db = FirebaseFirestore.getInstance();
        Button modificar = findViewById(R.id.btnModificar);
        EditText tip = findViewById(R.id.txtTipo);
        EditText nom = findViewById(R.id.txtNombre);
        EditText st = findViewById(R.id.numStock);
        consultaParametro(bundle.getString("NombreMed"));
        modificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usuario = usr.getEmail().toString();
                String codigo = bundle.getString("NombreMed");
                String tipo = tip.getText().toString();
                String nombre = nom.getText().toString();
                //String stck[] = st.getText().toString().split(" ");
                //System.out.println("STOCK: "+stck[0]);
                double stock = Double.parseDouble(st.getText().toString());

                MedicamentoDP medicamento = new MedicamentoDP(usuario, codigo, tipo, nombre, stock);
                insertar(medicamento);
                consultaParametro(medicamento.getCodMed());
            }
        });
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
                    EditText tipo = findViewById(R.id.txtTipo);
                    EditText nombre = findViewById(R.id.txtNombre);
                    EditText stock = findViewById(R.id.numStock);
                    tipo.setText(medicamento.getTipoMed());
                    nombre.setText(medicamento.getNomMed());
                    stock.setText(""+medicamento.getStockMed());
                }
            }
        });
    }

    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MODIFICACIÓN");
        builder.setMessage("Se ha modificado el medicamento de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido modificar el medicamento ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}