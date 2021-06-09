package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.enredados.petit.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AgendarCita extends AppCompatActivity {
    EditText etFecha;
    TextView tvHora;
    int tHour, tMinute;
    private Spinner tipoCitaSpinner;
    DatePickerDialog.OnDateSetListener setListener;
    private String paciente;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);
        paciente = getIntent().getStringExtra("paciente");

        etFecha = findViewById(R.id.et_fecha);
        tvHora = findViewById(R.id.tv_Hora);
        tipoCitaSpinner = findViewById(R.id.tipo_CitaSpinner);

        String[] tipoCita = {"Atención Clínica", "Atención Sanitaria", "Atención Estética"};
        ArrayAdapter<String> tipoCitaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoCita);
        tipoCitaSpinner.setAdapter(tipoCitaAdapter);

        Calendar calendario = Calendar.getInstance();
        final int year = calendario.get(Calendar.YEAR);
        final int month = calendario.get(Calendar.MONTH);
        final int day = calendario.get(Calendar.DAY_OF_MONTH);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AgendarCita.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        etFecha.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        tvHora.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        AgendarCita.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMinute = minute;
                                String time = tHour + ":" + tMinute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    tvHora.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(tHour,tMinute);
                timePickerDialog.show();
            }
        });
    }
    public void ingresar(View v){
        String tipoCita = tipoCitaSpinner.getSelectedItem().toString();
        String fecha = etFecha.getText().toString();
        String hora = tvHora.getText().toString();

        Map<String, Object> citas = new HashMap<>();
        citas.put("paciente", paciente);
        citas.put("tipo", tipoCita);
        citas.put("fecha", fecha);
        citas.put("hora", hora);
        citas.put("descripcion", "");
        citas.put("user", user.getEmail());

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("CITA").document((fecha+"-"+paciente).toString())
                .set(citas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        toastAgregado();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        toastError();
                    }
                });
        Intent lista = new Intent(v.getContext(), PacienteActivity.class);
        startActivity(lista);
    }
    private void toastAgregado(){
        Toast.makeText(this, "Cita agendada exitosamente",
                Toast.LENGTH_SHORT).show();
    }
    private void toastError(){
        Toast.makeText(this, "Se ha producido un error",
                Toast.LENGTH_SHORT).show();
    }
}