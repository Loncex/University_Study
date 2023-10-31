package com.example.app_universidad3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsGrupos extends AppCompatActivity {

    TextView detailsTextView;
    Bundle bundle;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_grupos);

        bundle = getIntent().getExtras();
        string = bundle.getString("key");

        detailsTextView = (TextView) findViewById(R.id.detailstextview);
        detailsTextView.setText(string);
    }
}