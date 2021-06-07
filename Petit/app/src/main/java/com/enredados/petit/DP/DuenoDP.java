package com.enredados.petit.DP;

public class DuenoDP {
    private String _cedula;
    private String _nombre;
    private String _apellido;
    private String _domicilio;
    private String _ciudad;
    private String _celular;
    private String _email;

    public DuenoDP(){
    }

    public DuenoDP(String cedula, String nombre, String apellido, String domicilio, String ciudad, String celular, String email) {
        _cedula = cedula;
        _nombre = nombre;
        _apellido = apellido;
        _domicilio = domicilio;
        _ciudad = ciudad;
        _celular = celular;
        _email = email;
    }
    public void setEmail(String email){
        _email = email;
    }

    public String getEmail(){
        return _email;
    }

    public String getCedula(){
        return _cedula;
    }

    public void setCedula(String idDueno){
        _cedula = idDueno;
    }

    public void setNombre(String nombre){
        _nombre = nombre;
    }

    public String getNombre(){
        return _nombre;
    }

    public void setApellido(String apellido){
        _apellido = apellido;
    }

    public String getApellido(){
        return _apellido;
    }

    public  void setDomicilio(String domicilio){
        _domicilio = domicilio;
    }

    public String getDomicilio(){
        return _domicilio;
    }

    public void setCiudad(String ciudad){
        _ciudad = ciudad;
    }

    public String getCiudad(){
        return _ciudad;
    }

    public void setCelular(String celular){
        _celular = celular;
    }

    public String getCelular(){
        return _celular;
    }

}
