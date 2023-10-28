package com.example.app_universidad3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Salir extends Fragment {

    private Button mButtonSignout;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salir, container, false);

        mAuth = FirebaseAuth.getInstance();

        mButtonSignout = (Button) view.findViewById(R.id.BtnSignout);
        mButtonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        return view;
    }
}