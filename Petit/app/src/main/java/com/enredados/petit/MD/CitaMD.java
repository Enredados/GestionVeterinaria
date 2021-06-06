package com.enredados.petit.MD;

import com.enredados.petit.DP.CitaDP;
import com.google.firebase.firestore.FirebaseFirestore;

public class CitaMD {

    private CitaDP citaDP;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean validacion;

    public CitaMD(CitaDP citaDP){ this.citaDP = citaDP; }

}
