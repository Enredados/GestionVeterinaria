package com.enredados.petit.complementos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enredados.petit.R;
import com.enredados.petit.complementos.Modelo;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Modelo> listItems;
    private Context context;

    public Adaptador(ArrayList<Modelo> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Modelo item = (Modelo) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_row, null);

        // componentes de la GUI
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        TextView tvTipo = (TextView)  convertView.findViewById(R.id.tvTipo);
        TextView tvFecha = (TextView)  convertView.findViewById(R.id.tvFecha);

        imgFoto.setImageResource(R.mipmap.ic_launcher);
        tvTipo.setText(item.getTipo());
        tvFecha.setText(item.getFecha());

        return convertView;

    }
}
