//BORRAR: BY JACK
package com.enredados.petit.MD;

import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;

import com.enredados.petit.DP.MedicamentoDP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MedicamentoMD {

    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    boolean validacion;


    public MedicamentoMD (){


    }

    public boolean insertar(String cod, String user, String tip, String nom, int stock){

        Map<String, Object> medicamentos = new HashMap<>();
        medicamentos.put("user", user);
        medicamentos.put("tipo", tip);
        medicamentos.put("nom", nom);
        medicamentos.put("stock", stock);

        db.collection("MEDICAMENTO").document(cod).set(medicamentos)
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


  /*  public boolean eliminar(String cod_med){

    }
    public boolean consultaGeneral(){

    }*/

    public MedicamentoDP consultaParametro(String cod){
             MedicamentoDP medicamento = new MedicamentoDP();
        db.collection("MEDICAMENTO").document(cod).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    MedicamentoDP medicamento = documentSnapshot.toObject(MedicamentoDP.class);
                    System.out.println(medicamento.getNomMed());
                }
            }
        });
        return medicamento;
      /* MedicamentoDP medicamento = new MedicamentoDP();
        DocumentReference docRef = db.collection("MEDICAMENTO").document(cod);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                 MedicamentoDP medicamento = documentSnapshot.toObject(MedicamentoDP.class);
                    System.out.println(medicamento.getNomMed());
            }

        });

        return medicamento;
        DocumentReference docRef = db.collection("MEDICAMENTO").document(cod);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                System.out.println("INGRESO A ON COMPLETE");
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                       // medicamento.setNomMed(document.get("user").toString());
                        medicamento.setNomMed(document.get("tipo").toString());
                        medicamento.setNomMed(document.get("nom").toString());
                        medicamento.setNomMed(document.get("stock").toString());
                        System.out.println("TIPO:"+medicamento.getTipoMed()+"\nNOMBRE: "+medicamento.getNomMed()+"\nSTOCK: "+medicamento.getStockMed());
                        validacion = true;
                    } else {
                        System.out.println("DOCUMENTO NO EXISTE");
                        validacion = false;
                    }
                } else {
                    System.out.println("DOCUMENTO NO SUCCESSFUL");
                    validacion = false;
                }
            }
        });
            return medicamento;*/
    }
   /* public boolean modificar(String cod_med){

    }*/
}
