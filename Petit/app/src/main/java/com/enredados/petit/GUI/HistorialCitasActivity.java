package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.enredados.petit.DP.CitaDP;
import com.enredados.petit.R;
import com.enredados.petit.complementos.Adaptador;
import com.enredados.petit.complementos.Modelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HistorialCitasActivity extends AppCompatActivity {


    private ListView lvItems;
    private Adaptador adaptador;
    FirebaseFirestore db;
    private String codigoPaciente;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas);

        db = FirebaseFirestore.getInstance();
        lvItems = (ListView) findViewById(R.id.lvItems);
        codigoPaciente = getIntent().getStringExtra("codigoPaciente");
        //ArrayList items = getArrayItems();
        ArrayList items = consultarCitas(codigoPaciente); //CAMBIAR EL CODIGO DEL PACIENTE POR EL EXTRA QUE VIENE DE LA ANTERIOR ACTIVIDAD
        /*adaptador = new Adaptador(items, this);
        lvItems.setAdapter(adaptador);
*/
        // para ver la actividad descripcion
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDescripcion = new Intent(view.getContext(), VisorDescripcion.class);

                Modelo temp = (Modelo) items.get(position);
                Toast.makeText(HistorialCitasActivity.this, "presionado: "+codigoPaciente, Toast.LENGTH_SHORT).show();

                visorDescripcion.putExtra("tipo", ((Modelo) items.get(position)).getTipo());
                visorDescripcion.putExtra("fecha", ((Modelo) items.get(position)).getFecha());
                visorDescripcion.putExtra("descripcion", ((Modelo) items.get(position)).getDescripcion());
                visorDescripcion.putExtra("codigoPaciente", codigoPaciente);
                startActivity(visorDescripcion);
            }
        });
    }

    // este metdodo tiene que interactuar con el dp y traer lodas las citas de la db
    private ArrayList<Modelo> getArrayItems(){
        ArrayList<Modelo> listItems = new ArrayList<>();
        Date now = new Date();
        String mensaje = "todo bien con el perro, tiene rabia y kobik";

        listItems.add(new Modelo("Medica", mensaje, "15/may/2021"));
        listItems.add(new Modelo("Peluqueria", "sin observaciones", "12/jun/2021"));
        listItems.add(new Modelo("Vacunacion", mensaje, "05/abril/2021"));
        listItems.add(new Modelo("Medica", mensaje, "23/feb/2021"));
        listItems.add(new Modelo("Medica", mensaje, "15/may/2021"));
        listItems.add(new Modelo("Peluqueria", "sin observaciones", "12/jun/2021"));
        listItems.add(new Modelo("Vacunacion", mensaje, "05/abril/2021"));
        listItems.add(new Modelo("Medica", mensaje, "23/feb/2021"));

        return listItems;
    }

    // poner de parametro el codigo de la mascota a la que se hace referencia
    private ArrayList<Modelo> consultarCitas(String codigoPaciente) {
        ArrayList<Modelo> citasArrayList = new ArrayList<>();
        try {
            db.collection("CITA")
                    .whereEqualTo("paciente", codigoPaciente)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    citasArrayList.add(new Modelo(
                                            document.get("tipo").toString(),
                                            document.get("descripcion").toString(),
                                            document.get("fecha").toString()
                                    ));

                                    cargarCitas(citasArrayList);
                                }
                            }
                            else{
                            }
                        }
                    });
       } catch (Exception e) {
            citasArrayList.add(new Modelo("Oops", "oops", "15/may/2021 oops"));
        }
        return citasArrayList;
    }

    public void cargarCitas(ArrayList<Modelo> citas){
        adaptador = new Adaptador(citas, this);
        lvItems.setAdapter(adaptador);
    }
}