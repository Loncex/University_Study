package com.example.app_universidad3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    //Variable del boton que te lleva al registro de users
    Button UsuarioNuevoTXT;

    //Variables del login de users
    private EditText CorreoEt;
    private EditText ContraseñaEt;
    private Button LoginUsuario;

    //Variables de confirmacion de campos
    private String email = "";
    private String password = "";

    //Variable que llama a FirebaseAuth
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        CorreoEt = (EditText) findViewById(R.id.CorreoLogin);
        ContraseñaEt = (EditText) findViewById(R.id.PassLogin);
        LoginUsuario = (Button) findViewById(R.id.Btn_Logeo);

        UsuarioNuevoTXT = findViewById(R.id.UsuarioNuevoTXT);

        LoginUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = CorreoEt.getText().toString();
                password = ContraseñaEt.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(Login.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UsuarioNuevoTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, MenuPrincipal.class));
                }else{
                    Toast.makeText(Login.this, "No se pudo iniciar sesion. Por favor compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this, MenuPrincipal.class));
            finish();
        }
    }
}