package com.example.app_universidad3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Integrantes extends AppCompatActivity {

    RecyclerView recyclerView2;
    ArrayList<String> arrayList;
    GruposAdapter gruposAdapter;
    EditText editTextGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_id);

        editTextGrupo = (EditText) findViewById(R.id.edit_text_grupos);

        arrayList = new ArrayList<String>();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(linearLayoutManager);
        gruposAdapter = new GruposAdapter(arrayList, this);
        recyclerView2.setAdapter(gruposAdapter);
    }

    public void addItem(View view){
        String text = editTextGrupo.getText().toString();
        arrayList.add(text);
        int position = arrayList.indexOf(text);
        gruposAdapter.notifyItemInserted(position);
        editTextGrupo.setText("");

    }

    public void deleteItem(View view){
        String text = editTextGrupo.getText().toString();
        int position = arrayList.indexOf(text);
        arrayList.remove(position);
        gruposAdapter.notifyItemRemoved(position);
        editTextGrupo.setText("");

    }
}