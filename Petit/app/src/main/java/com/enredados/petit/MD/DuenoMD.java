package com.enredados.petit.MD;

import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.enredados.petit.DP.DuenoDP;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DuenoMD {
    private DuenoDP _dueno;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public DuenoMD (){
    }
    public DuenoMD(DuenoDP dueno){
         _dueno = dueno;
    }

    public boolean insertar(){

        Map<String, Object> duenos = new HashMap<>();
        duenos.put("nombre", _dueno.getID());
        duenos.put("apellido", _dueno.getApellido());
        duenos.put("domicilio", _dueno.getDomicilio());
        duenos.put("ciudad", _dueno.getCiudad());
        duenos.put("celular",_dueno.getCelular());


        db.collection("DUENO").document(_dueno.getID())
                .set(duenos)
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