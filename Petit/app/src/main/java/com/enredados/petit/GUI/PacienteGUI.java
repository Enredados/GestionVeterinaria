package com.enredados.petit.GUI;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.enredados.petit.DP.PacienteDP;
import com.enredados.petit.databinding.ActivityListViewBinding;

import java.util.ArrayList;
import com.enredados.petit.MD.PacienteMD;

public class PacienteGUI extends AppCompatActivity{
    ActivityListViewBinding binding;
    private PacienteMD pacienteMD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*int[] imageId = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,
                R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i};
        String[] name = {"Christopher","Craig","Sergio","Mubariz","Mike","Michael","Toa","Ivana","Alex"};
        String[] lastMessage = {"Heye","Supp","Let's Catchup","Dinner tonight?","Gotta go",
                "i'm in meeting","Gotcha","Let's Go","any Weekend Plans?"};
        String[] lastmsgTime = {"8:45 pm","9:00 am","7:34 pm","6:32 am","5:76 am",
                "5:00 am","7:34 pm","2:32 am","7:76 am"};
        String[] phoneNo = {"7656610000","9999043232","7834354323","9876543211","5434432343",
                "9439043232","7534354323","6545543211","7654432343"};
        String[] country = {"United States","Russia","India","Israel","Germany","Thailand","Canada","France","Switzerland"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            User user = new User(name[i],lastMessage[i],lastmsgTime[i],phoneNo[i],country[i],imageId[i]);
            userArrayList.add(user);

        }
*/
        ArrayList<PacienteDP> pacientes = new ArrayList<PacienteDP>();
        pacientes = pacienteMD.consultaGeneralMD();
        ItemListaActivity itemListaActivity = new ItemListaActivity(PacienteGUI.this,pacientes);

        binding.listview.setAdapter(itemListaActivity);
        binding.listview.setClickable(true);
        ArrayList<PacienteDP> finalPacientes = pacientes;
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(PacienteGUI.this, PacienteActivity.class);
                i.putExtra("nombre", finalPacientes.get(position).getNombre());
                i.putExtra("especie", finalPacientes.get(position).getEspecie());
                i.putExtra("raza", finalPacientes.get(position).getRaza());
                i.putExtra("genero", finalPacientes.get(position).getGenero());
                i.putExtra("peso", finalPacientes.get(position).getPeso());
                i.putExtra("edad", finalPacientes.get(position).getEdad());
                i.putExtra("imagenId", finalPacientes.get(position).getImagenId());
                startActivity(i);

            }
        });

    }
}
