package com.enredados.petit.GUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.enredados.petit.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class AuthActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;
    private SharedPreferences pref;
    private EditText emailView;
    private EditText passView;
    private View authLayout;
    private Button registrar;
    private Button ingresar;
    private Button google;
    private int GOOGLE_SIGN_IN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        GOOGLE_SIGN_IN = 100;

        Bundle bundle = new Bundle();
        bundle.putString("message", "Integracion de firebase completa");
        mFirebaseAnalytics.logEvent("InitScreen", bundle);

        emailView = findViewById(R.id.authLayout);
        passView = findViewById(R.id.passwordEditText);
        registrar = findViewById(R.id.signUpButton);
        ingresar = findViewById(R.id.logInButton);
        authLayout = findViewById(R.id.authLayout);
        google = findViewById(R.id.googleButton);
        //setup
        setup();

    }

    @Override
    protected void onStart() {
        super.onStart();
        authLayout.setVisibility(View.VISIBLE);
    }

    public void setup() {


        setTitle("Autenticación");
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegistro();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailView.getText().toString().isEmpty() && !passView.getText().toString().isEmpty()) {
                    System.out.println(emailView.getText().toString() + " " + passView.getText().toString());

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailView.getText().toString(), passView.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        showHome(task.getResult().getUser().getEmail(),ProviderType.BASIC);
                                    }
                                    else
                                    {
                                        showAlert();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void googleSignIn(View view)
    {
        GoogleSignInOptions googleConf = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        GoogleSignInClient googleClient = GoogleSignIn.getClient(this, googleConf);
       // googleClient.signOut();

        startActivityForResult(googleClient.getSignInIntent(), GOOGLE_SIGN_IN);
    }


    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ERROR");
        builder.setMessage("Se ha encontrando un error en la autenticacion");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHome(String email, ProviderType provider) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("email", email);
        homeIntent.putExtra("provider", provider);
        startActivity(homeIntent);
    }

    private void showRegistro()
    {
        Intent registroIntent = new Intent(this, RegistroActivity.class);
        startActivity(registroIntent);
    }

    private void sesion(){
        pref = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = pref.getString("email", null);
        String provider = pref.getString("provider", null);

        if(email != null && provider != null)
        {
            authLayout.setVisibility(View.INVISIBLE);
            showHome(email, ProviderType.valueOf(provider));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null)
                {
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    FirebaseAuth.getInstance().signInWithCredential(credential);

                    if(task.isSuccessful())
                    {
                        showHome(account.getEmail(),ProviderType.GOOGLE);
                    }
                    else
                    {
                        showAlert();
                    }
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}
