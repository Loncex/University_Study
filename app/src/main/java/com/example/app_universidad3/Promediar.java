package com.example.app_universidad3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Promediar extends Fragment {

    private EditText editTextNota1, editTextNota2, editTextNota3, editTextNota4;
    private EditText editTextPonderacion1, editTextPonderacion2, editTextPonderacion3, editTextPonderacion4;
    private Button calcularButton;
    private TextView textViewResultado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promediar, container, false);

        editTextNota1 = view.findViewById(R.id.editTextNota1);
        editTextPonderacion1 = view.findViewById(R.id.editTextPonderacion1);
        editTextNota2 = view.findViewById(R.id.editTextNota2);
        editTextPonderacion2 = view.findViewById(R.id.editTextPonderacion2);
        editTextNota3 = view.findViewById(R.id.editTextNota3);
        editTextPonderacion3 = view.findViewById(R.id.editTextPonderacion3);
        editTextNota4 = view.findViewById(R.id.editTextNota4);
        editTextPonderacion4 = view.findViewById(R.id.editTextPonderacion4);
        // Repite para las otras notas y ponderaciones

        calcularButton = view.findViewById(R.id.calcularButton);
        textViewResultado = view.findViewById(R.id.textViewResultado);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularPromedio();
            }
        });
        
        return view;
    }

    private void calcularPromedio() {
        double nota1 = Double.parseDouble(editTextNota1.getText().toString());
        double ponderacion1 = Double.parseDouble(editTextPonderacion1.getText().toString());
        double nota2 = Double.parseDouble(editTextNota2.getText().toString());
        double ponderacion2 = Double.parseDouble(editTextPonderacion2.getText().toString());
        double nota3 = Double.parseDouble(editTextNota3.getText().toString());
        double ponderacion3 = Double.parseDouble(editTextPonderacion3.getText().toString());
        double nota4 = Double.parseDouble(editTextNota4.getText().toString());
        double ponderacion4 = Double.parseDouble(editTextPonderacion4.getText().toString());

        if (nota1 > 70 || nota2 > 70 || nota3 > 70 || nota4 > 70) {
            Toast.makeText(requireContext(), "Las notas no pueden ser mayores a 70", Toast.LENGTH_SHORT).show();
            return; // Detener el cálculo
        }

        if (ponderacion1 + ponderacion2 + ponderacion3 + ponderacion4 != 100) {
            Toast.makeText(requireContext(), "La suma de las ponderaciones debe ser igual a 100%", Toast.LENGTH_SHORT).show();
            return; // Detener el cálculo
        }

        double promedio = (nota1 * ponderacion1 + nota2 * ponderacion2 + nota3 * ponderacion3 + nota4 * ponderacion4) / (ponderacion1 + ponderacion2 + ponderacion3 + ponderacion4);

        DecimalFormat df = new DecimalFormat("#.##"); // Redondear a 2 decimales
        String resultado = "Su promedio es: " + df.format(promedio);

        textViewResultado.setText(resultado);
    }


}