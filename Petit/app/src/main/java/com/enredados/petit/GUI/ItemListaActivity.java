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


    public ItemListaActivity(Context context, ArrayList<PacienteDP> userArrayList){

        super(context, R.layout.activity_item_lista,userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        PacienteDP user = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_lista,parent,false);

        }

        ImageView imagenVista = convertView.findViewById(R.id.mascotaImagen);
        TextView usuarioNombre = convertView.findViewById(R.id.mascotaNombre);
        TextView msg = convertView.findViewById(R.id.mensaje);

        imagenVista.setImageResource(user.getImagenId());
        usuarioNombre.setText(user.getNombre());
        msg.setText(user.getMensaje());


        return convertView;
    }
}

