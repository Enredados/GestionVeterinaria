package com.enredados.petit.MD;
import androidx.annotation.NonNull;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.DP.PacienteDP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PacienteMD {
    private PacienteDP pacienteDP;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public PacienteMD (){
    }
    public PacienteMD(PacienteDP pacienteDP){
        this.pacienteDP = pacienteDP;
    }

    public boolean insertarMD(){

        Map<String, Object> pacientes = new HashMap<>();
        pacientes.put("codigo", pacienteDP.getCodigo());
        pacientes.put("nombre", pacienteDP.getNombre());
        pacientes.put("especie", pacienteDP.getEspecie());
        pacientes.put("raza", pacienteDP.getRaza());
        pacientes.put("genero",pacienteDP.getGenero());
        pacientes.put("peso", pacienteDP.getPeso());
        pacientes.put("edad",pacienteDP.getEdad());


        db.collection("PACIENTE").document(pacienteDP.getCodigo())
                .set(pacientes)
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
   /*public ArrayList<PacienteDP> consultaGeneralMD(){
        validacion = false;
        ArrayList<PacienteDP> pacientes = new ArrayList<PacienteDP>();
        db.collection("PACIENTE")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PacienteDP paciente = new PacienteDP();
                                paciente.setCodigo(document.get("codigo").toString());
                                paciente.setNombre(document.get("nombre").toString());
                                paciente.setEspecie(document.get("especie").toString());
                                paciente.setRaza(document.get("raza").toString());
                                paciente.setGenero(document.get("genero").toString());
                                paciente.setPeso(Double.parseDouble(document.get("peso").toString()));
                                paciente.setEdad(Integer.parseInt(document.get("edad").toString()));
                                pacientes.add(paciente);
                            }
                        }
                    }
                });
        return pacientes;
    }*/
}
