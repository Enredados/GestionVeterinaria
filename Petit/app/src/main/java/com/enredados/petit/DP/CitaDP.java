package com.enredados.petit.DP;

public class CitaDP {

    private String codigo;
    private String fecha;
    private String tipo;
    private String hora;
    private String descripcion;

    public CitaDP(String codigo, String fecha, String tipo, String hora, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public CitaDP() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
