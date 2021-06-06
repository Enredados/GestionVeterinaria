package com.enredados.petit.MD;

import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.enredados.petit.DP.MedicamentoDP;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MedicamentoMD {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public MedicamentoMD (){
    }

    public boolean insertar(String cod, String user, String tipo, String nom, int stock){

        Map<String, Object> medicamentos = new HashMap<>();
        medicamentos.put("user", user);
        medicamentos.put("tipo_med", tipo);
        medicamentos.put("nom_med", nom);
        medicamentos.put("stock_med", stock);

        db.collection("MEDICAMENTO").document(cod)
                .set(medicamentos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        validacion = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        validacion = false;
                    }
                });
        return validacion;
    }


    /*public boolean eliminar(String cod_med){

    }
    public boolean consultaGeneral(){

    }
    public boolean consultaParametro(String cod_med){

    }
    public boolean modificar(String cod_med){

    }*/
}
