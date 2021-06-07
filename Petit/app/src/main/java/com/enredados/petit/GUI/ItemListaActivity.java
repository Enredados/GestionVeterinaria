package com.enredados.petit.GUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enredados.petit.DP.PacienteDP;
import com.enredados.petit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemListaActivity extends ArrayAdapter<PacienteDP> {


    public ItemListaActivity(Context context, ArrayList<PacienteDP> pacienteArrayList){

        super(context, R.layout.activity_item_lista,pacienteArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PacienteDP pacienteDP = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_lista,parent,false);

        }

        ImageView mascotaImagen = convertView.findViewById(R.id.mascotaImagen);
        TextView mascotaNombre = convertView.findViewById(R.id.mascotaNombre);

        mascotaImagen.setImageResource(pacienteDP.getImagenId());
        mascotaNombre.setText(pacienteDP.getNombre());


        return convertView;
    }
}

