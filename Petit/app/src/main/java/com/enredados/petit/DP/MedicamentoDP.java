package com.enredados.petit.DP;

import com.enredados.petit.MD.MedicamentoMD;

public class MedicamentoDP {
    private String user;
    private String cod_med;
    private String tipo_med;
    private String nom_med;
    private int stock_med;
    private MedicamentoMD medicamentoMD;

    public MedicamentoDP(String user, String cod_med, String tipo_med, String nom_med, int stock_med){
        medicamentoMD = new MedicamentoMD();
        this.user = user;
        this.cod_med = cod_med;
        this.tipo_med = tipo_med;
        this.nom_med = nom_med;
        this.stock_med = stock_med;
    }

    public MedicamentoDP(){
    }
    public void setUser(String user){
        this.user = user;
    }
    public void setCodMed(String cod_med){
        this.cod_med = cod_med;
    }
    public void setTipoMed(String tipo_med){
        this.tipo_med = tipo_med;
    }
    public void setNomMed(String nom_med){
        this.nom_med = nom_med;
    }
    public void setStockMed(int stock_med){
        this.stock_med = stock_med;
    }
    public String getUser(){
        return this.user;
    }
    public String getCodMed(){
        return this.cod_med;
    }
    public String getTipoMed(){
        return this.tipo_med;
    }
    public String getNomMed(){
        return this.nom_med;
    }
    public int getStockMed(){
        return this.stock_med;
    }
    public boolean guardarMed(){
        return medicamentoMD.insertar(cod_med, user, tipo_med, nom_med, stock_med);
    }

}

