package com.example.app_universidad3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Apuntes extends AppCompatActivity {

    FloatingActionButton agregarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuntes);

        agregarNota = findViewById(R.id.BtnAgregarNota);

        agregarNota.setOnClickListener((v -> startActivity(new Intent(Apuntes.this, AgregarNotas.class))));

    }
}