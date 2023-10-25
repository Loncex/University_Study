package com.example.app_universidad3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Inicio extends Fragment {

    //Los botones que se encuentran en mi inicio
    Button BtnApuntes, BtnGrupos;

    private TextView nombresPrincipal;
    private TextView correoPrincipal;

    private ProgressBar progressBarDatos;

    private FirebaseAuth mAuth;

    //Variable que llamara a mis datos dentro de database realtime
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressBarDatos = (ProgressBar) view.findViewById(R.id.progressBarDatos);

        nombresPrincipal = (TextView) view.findViewById(R.id.NombresPrincipal);
        correoPrincipal = (TextView) view.findViewById(R.id.CorreoPrincipal);

        BtnApuntes = (Button) view.findViewById(R.id.BtnApuntes);
        BtnGrupos = (Button) view.findViewById(R.id.BtnGrupos);

        BtnApuntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), Apuntes.class));
            }
        });

        BtnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), Grupos.class));
            }
        });

        getUserInfo();

        return view;
    }

    private void getUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    progressBarDatos.setVisibility(View.GONE);

                    nombresPrincipal.setVisibility(View.VISIBLE);
                    correoPrincipal.setVisibility(View.VISIBLE);

                    String name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    nombresPrincipal.setText(name);
                    correoPrincipal.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}