package com.enredados.petit.DP;
import com.enredados.petit.MD.PacienteMD;
import java.util.ArrayList;

public class PacienteDP  {

    private String codigo, nombre, especie, raza, genero;
    private double peso;
    private int imagenId, edad;
    private PacienteMD pacienteMD;

    public PacienteDP(String codigo, String nombre, String especie, String raza,
                String genero, double peso, int imagenId, int edad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        this.peso = peso;
        this.imagenId = imagenId;
        this.edad = edad;
    }


    public PacienteDP() {
        pacienteMD = new PacienteMD(this);
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getImagenId() { return imagenId; }

    public void setImagenId(int imagenId) { this.imagenId = imagenId; }


    public boolean insertarDP(){
        return pacienteMD.insertarMD();
    }

    public ArrayList<PacienteDP> consultaGeneral(){
        return pacienteMD.consultaGeneralMD();
    }
/*
    public boolean consultarDP(){
        return pacienteMD.consultarMD();
    }

    public boolean modificarDP(){
        return pacienteMD.actualizarMD();
    }

    public boolean eliminarDP(){
        return pacienteMD.eliminarMD();
    }

    public boolean verificarExisteDP(){
        return pacienteMD.verificarExisteMD();
    }


     */
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            String country = intent.getStringExtra("country");
            int imageid = intent.getIntExtra("imageid",R.drawable.a);

            binding.nameProfile.setText(name);
            binding.phoneProfile.setText(phone);
            binding.countryProfile.setText(country);
            binding.profileImage.setImageResource(imageid);
        }
    }*/

}
