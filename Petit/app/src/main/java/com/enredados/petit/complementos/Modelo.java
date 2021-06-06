package com.enredados.petit.complementos;

import java.text.DateFormat;

public class Modelo {

    private String tipo;
    private DateFormat fecha;

    public Modelo(String tipo, DateFormat fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DateFormat getFecha() {
        return fecha;
    }

    public void setFecha(DateFormat fecha) {
        this.fecha = fecha;
    }


}
