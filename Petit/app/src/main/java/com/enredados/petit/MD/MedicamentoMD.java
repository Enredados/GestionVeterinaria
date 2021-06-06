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
    private MedicamentoDP medicamento;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public MedicamentoMD (){
    }
    public MedicamentoMD(MedicamentoDP medicamento){
        this.medicamento = medicamento;
    }

    public boolean insertar(){

        Map<String, Object> medicamentos = new HashMap<>();
        medicamentos.put("user", medicamento.getUser());
        medicamentos.put("tipo_med", medicamento.getTipoMed());
        medicamentos.put("nom_med", medicamento.getNomMed());
        medicamentos.put("stock_med", medicamento.getStockMed());

        db.collection("MEDICAMENTO").document(medicamento.getCodMed())
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
