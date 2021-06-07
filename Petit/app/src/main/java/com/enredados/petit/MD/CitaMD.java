package com.enredados.petit.MD;

import androidx.annotation.NonNull;

import com.enredados.petit.DP.CitaDP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CitaMD {

    private CitaDP citaDP;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean validacion;

    public CitaMD(CitaDP citaDP){ this.citaDP = citaDP; }

    public ArrayList<CitaDP> consultarTodos(){
        validacion = false;
        ArrayList<CitaDP> citas = new ArrayList<>();
        db.collection("CITA")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                CitaDP cita = new CitaDP();
                                cita.setCodigo(document.get("codigo").toString());
                                cita.setDescripcion(document.get("descripcion").toString());
                                //cita.setFecha(document.get("fecha"));
                                cita.setTipo(document.get("tipo").toString());
                            }
                        }
                    }
                });
        return null;
    }



}
