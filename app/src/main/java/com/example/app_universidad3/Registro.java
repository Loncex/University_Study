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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    Button TengounacuentaTXT;

    private EditText NombreEt;
    private EditText CorreoEt;
    private EditText ContraseñaEt;
    private Button RegistrarUsuario;

    //Variables de los datos
    private String email ="";
    private String password="";
    private String name="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_registro);

        NombreEt = (EditText) findViewById(R.id.NombreEt);
        CorreoEt = (EditText) findViewById(R.id.CorreoEt);
        ContraseñaEt = (EditText) findViewById(R.id.ContraseñaEt);
        RegistrarUsuario = (Button) findViewById(R.id.RegistrarUsuario);

        TengounacuentaTXT = (Button) findViewById(R.id.TengounacuentaTXT);


        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = NombreEt.getText().toString();
                email = CorreoEt.getText().toString();
                password = ContraseñaEt.getText().toString();

                if(!name.isEmpty() && !email.isEmpty()  && !password.isEmpty()){
                    if(password.length() >= 6){

                    }else{
                        Toast.makeText(Registro.this, "La contraseña debe tener un minimo de 6 caracteres ", Toast.LENGTH_SHORT).show();
                    }

                    registerUser();
                }
                else{
                    Toast.makeText(Registro.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TengounacuentaTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this, Login.class));
            }
        });

    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("ERRORRRRRR!: ");
                        System.out.println(e.toString());
                    }
                });
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Registro.this, MenuPrincipal.class));
                                finish();
                            } else {
                                Toast.makeText(Registro.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Registro.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}