package com.enredados.petit.MD;

import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.enredados.petit.DP.DuenoDP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuenoMD {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public DuenoMD (){
    }

    public boolean insertar(String cedula, String nombre, String apellido, String domicilio, String ciudad, String celular){

        Map<String, Object> duenos = new HashMap<>();
        duenos.put("nombre", nombre);
        duenos.put("apellido", apellido);
        duenos.put("domicilio", domicilio);
        duenos.put("ciudad", ciudad);
        duenos.put("celular", celular);

        db.collection("PROPIETARIO").document(cedula)
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

    public ArrayList<DuenoDP> consultaGeneral(){
        validacion = false;
        ArrayList<DuenoDP> duenos = new ArrayList<DuenoDP>();
        db.collection("PROPIETARIO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DuenoDP dueno = new DuenoDP();
                                dueno.setCedula("123123");
                                dueno.setNombre(document.get("nombre").toString());
                                dueno.setApellido(document.get("apellido").toString());
                                dueno.setDomicilio(document.get("domicilio").toString());
                                dueno.setCiudad(document.get("ciudad").toString());
                                dueno.setCelular(document.get("celular").toString());
                                duenos.add(dueno);
                            }
                        }
                    }
                });
        return duenos;
    }

    /*
    public boolean consultaParametro(String cod_med){

    }
    public boolean modificar(String cod_med){

    }*/
}