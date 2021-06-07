package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enredados.petit.DP.MedicamentoDP;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConsultaParametroMed extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_parametro_med);
         db = FirebaseFirestore.getInstance();
        Button consultar = findViewById(R.id.ConsultarMed);
        EditText cod = findViewById(R.id.TextCodMed);
        setTitle("CONSULTA POR PARÁMETRO");
        consultar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String codigo = cod.getText().toString();
                consultaParametro(codigo);
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
                    TextView user = findViewById(R.id.TextUser);
                    TextView tipo = findViewById(R.id.TextTipo);
                    TextView nombre = findViewById(R.id.TextNombre);
                    TextView stock = findViewById(R.id.TextStock);
                    user.setText(medicamento.getUser());
                    tipo.setText(medicamento.getTipoMed());
                    nombre.setText(medicamento.getNomMed());
                    stock.setText(""+medicamento.getStockMed());
                }
            }
        });
    }

}