package com.example.app_universidad3;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Grupos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        ListView listView = findViewById(R.id.listView);
        List<String> list = new ArrayList<>();
        list.add("Grupo 1");
        list.add("Grupo 2");
        list.add("Grupo 3");
        list.add("Grupo 4");
        list.add("Grupo 5");
        list.add("Grupo 6");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                    startActivity(new Intent(Grupos.this,Integrantes.class));

                }else if(position==1){

                    startActivity(new Intent(Grupos.this,Integrantes.class));
                }else if(position==2){

                    startActivity(new Intent(Grupos.this,Integrantes.class));

                } else if (position==3) {

                    startActivity(new Intent(Grupos.this,Integrantes.class));

                } else if (position==4) {

                    startActivity(new Intent(Grupos.this,Integrantes.class));

                } else if (position==5) {

                    startActivity(new Intent(Grupos.this,Integrantes.class));

                }else{

                }
            }
        });



    }

}