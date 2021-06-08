package com.enredados.petit.GUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.enredados.petit.DP.DuenoDP;
import com.enredados.petit.DP.PacienteDP;
import com.enredados.petit.R;
import com.enredados.petit.databinding.ActivityPacienteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PacienteActivity extends AppCompatActivity {
    ListView listaMascotas;
    AdapterView adapter;
    ArrayList<PacienteDP> pacientes = new ArrayList<PacienteDP>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    final static String ACT_INFO = "com.enredados.petit.GUI.PerfilMascota";
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        listaMascotas = (ListView) findViewById(R.id.listaMascotas);
        mostrarListaPacientes();

        listaMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent registro = new Intent(view.getContext(), PerfilMascota.class);
                PacienteDP paciente = pacientes.get(position);
                String[] info = new String[2];
                info[0] = paciente.getNombre();
                info[1] = paciente.getCodigo();
                registro.putExtra(ACT_INFO, info);
                registro.putExtra("codigoPaciente", paciente.getCodigo());
                startActivity(registro);
            }
        });
        //Elimina mascota con click sostenido
        listaMascotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PacienteDP paciente = pacientes.get(position);
                String[] infor = new String[1];
                infor[0] = paciente.getCodigo();
                new AlertDialog.Builder(PacienteActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Está seguro de eliminar?")
                        .setMessage("¿Desea eliminar esta Mascota?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("PACIENTE").document(infor[0])
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                showSucces();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull @NotNull Exception e) {
                                                showAlert();
                                            }
                                        });
                                mostrarListaPacientes();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    private void showSucces() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("ELIMINADO");
        builder.setMessage("Se ha eliminado la mascota de manera exitosa ");
        builder.setPositiveButton("Aceptar", null);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlert() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("No se a podido eliminar la mascota");
        builder.setPositiveButton("Aceptar", null);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void mostrarListaPacientes() {
        db = FirebaseFirestore.getInstance();
        db.collection("PACIENTE").whereEqualTo("user", user.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<String> nombre = new ArrayList<String>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PacienteDP paciente = new PacienteDP();
                                nombre.add(document.get("nombre").toString());
                                paciente.setCodigo(document.getId().toString());
                                paciente.setNombre(document.get("nombre").toString());
                                paciente.setEspecie(document.get("especie").toString());
                                paciente.setRaza(document.get("raza").toString());
                                paciente.setGenero(document.get("genero").toString());
                                paciente.setPeso(document.get("peso").toString());
                                paciente.setEdad(document.get("edad").toString());
                                pacientes.add(paciente);
                            }
                            setLista(nombre);
                        } else {
                        }
                    }
                });
    }
    private void setLista(ArrayList<String> nombre) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombre);
        listaMascotas.setAdapter(arrayAdapter);
    }

    public void abrirRegistro(View vista) {
        Intent registro = new Intent(this, IngresoPacienteActivity.class);
        String[] info = new String[1];

        registro.putExtra(ACT_INFO, info);
        startActivity(registro);
    }


    protected void onResume(Bundle savedInstanceState) {
        mostrarListaPacientes();

    }

}
