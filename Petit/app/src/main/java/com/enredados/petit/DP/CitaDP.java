package com.enredados.petit.DP;

import com.enredados.petit.MD.CitaMD;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class CitaDP {

    private String codigo;
    private DateFormat fecha;
    private String tipo;
    private String descripcion;
    private CitaMD citaMD;

    public CitaDP(String codigo, DateFormat fecha, String tipo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipo = tipo;
        citaMD = new CitaMD(this);
    }

    public CitaDP(String descripcion, DateFormat fecha){
        this.fecha = fecha;
        this.descripcion = descripcion;
        citaMD = new CitaMD(this);
    }

    public CitaDP(String codigo, DateFormat fecha, String tipo, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        citaMD = new CitaMD(this);
    }


    public CitaDP() {
        citaMD = new CitaMD(this);
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public DateFormat getFecha() {
        return fecha;
    }

    public void setFecha(DateFormat fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion(){ return descripcion; }

    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }

    public ArrayList<CitaDP> consultarHistorialCitas(){
        return null;
        //return citaMD.consultarTodasDescripciones();
    }

    /*
    public boolean insertarDP(){
        return citaMD.insertarMD();
    }

    public boolean consultarDP(){
        return citaMD.consultarMD();
    }

    public boolean modificarDP(){
        return citaMD.actualizarMD();
    }

    public boolean eliminarDP(){
        return citaMD.eliminarMD();
    }

    public boolean verificarExisteDP(){
        return citaMD.verificarExisteMD();
    }

    public ArrayList<PacienteDP> consultarTodosDP(){
        return citaMD.consultarTodosMD();
    }
     */
}
