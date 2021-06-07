package com.enredados.petit.complementos;

import java.text.DateFormat;

public class Modelo {

    private String tipo;
    private String descripcion;
    private String fecha;

    public Modelo(String tipo, String descripcion, String fecha) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Modelo() {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
