package com.enredados.petit.GUI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enredados.petit.DP.MedicamentoDP;
import com.enredados.petit.MD.MedicamentoMD;
import com.enredados.petit.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class IngresoMed extends AppCompatActivity {
    FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_med);

    }
    public void registro(MedicamentoDP medicamento)
    {
       // EditText us =  findViewById(R.id.UsuarioMed);
        EditText cod = findViewById(R.id.CodigoMed);
        EditText tip = findViewById(R.id.TipoMed);
        EditText nom = findViewById(R.id.NombreMed);
        EditText st = findViewById(R.id.StockMed);
//        String usuario = us.getText().toString();
        String usuario = usr.getEmail();
        String codigo = cod.getText().toString();
        String tipo = tip.getText().toString();
        String nombre = nom.getText().toString();
        int stock = Integer.parseInt(st.getText().toString());

        Button agregar = findViewById(R.id.AgregarMed);

        setTitle("REGISTRO");

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MedicamentoMD med = new MedicamentoMD(medicamento);
               if(med.insertar()){
                   MedicamentoDP medicamento = new MedicamentoDP(usuario, codigo, tipo, nombre, stock);
                   showSucces();
               }
               else{
                   showAlert();
               }
            }
        });



    }
    private void showSucces() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("REGISTRO");
        builder.setMessage("Se ha creado la cuenta de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido registrar la cuenta");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}