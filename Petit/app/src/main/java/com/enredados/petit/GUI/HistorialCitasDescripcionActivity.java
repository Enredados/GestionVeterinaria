package com.enredados.petit.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enredados.petit.R;

import org.w3c.dom.Text;

public class HistorialCitasDescripcionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_citas_descripcion);

        TextView textView = findViewById(R.id.txtViewDescripcion);
        textView.setText(getIntent().getStringExtra("descripcion"));




    }
}