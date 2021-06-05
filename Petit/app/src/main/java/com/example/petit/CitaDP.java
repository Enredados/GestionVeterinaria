package com.example.petit;

import java.text.DateFormat;

public class CitaDP {

    private String codigo;
    private DateFormat fecha;
    private String tipo;
    //private CitaMD citaMD;

    public CitaDP(String codigo, DateFormat fecha, String tipo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    /*
    public CitaDP() {
        citaMD = new CitaMD(this);
    }
     */

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
