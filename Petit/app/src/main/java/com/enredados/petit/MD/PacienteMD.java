package com.enredados.petit.MD;
import androidx.annotation.NonNull;

import com.enredados.petit.DP.PacienteDP;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PacienteMD {
    private PacienteDP pacienteDP;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean validacion;

    public PacienteMD (){
    }
    public PacienteMD(PacienteDP pacienteDP){
        this.pacienteDP = pacienteDP;
    }

    public boolean insertarMD(){

        Map<String, Object> pacientes = new HashMap<>();
        pacientes.put("codigo", pacienteDP.getCodigo());
        pacientes.put("nombre", pacienteDP.getNombre());
        pacientes.put("especie", pacienteDP.getEspecie());
        pacientes.put("raza", pacienteDP.getRaza());
        pacientes.put("genero",pacienteDP.getGenero());
        pacientes.put("peso", pacienteDP.getPeso());
        pacientes.put("edad",pacienteDP.getEdad());


        db.collection("PACIENTE").document(pacienteDP.getCodigo())
                .set(pacientes)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        validacion = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        validacion = false;
                    }
                });
        return validacion;
    }
    /*
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        bd.insert("votantes", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,colegio,nromesa  from votantes where dni=" + dni, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicho dni",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        int cant = bd.delete("votantes", "dni=" + dni, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró la persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        int cant = bd.update("votantes", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe una persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
    }

     */
}
