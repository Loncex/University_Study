package com.example.app_universidad3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.IOException;
import java.util.function.ToLongBiFunction;

public class ReconocerTexto extends AppCompatActivity {

    private Button ReconocerTexto;
    private ImageView image;
    private EditText TextoReconocidoEt;

    private Uri uri = null;

    private TextRecognizer textRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocer_texto);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ReconocerTexto = (Button) findViewById(R.id.ReconocerTexto);
        image = (ImageView) findViewById(R.id.image);
        TextoReconocidoEt = (EditText) findViewById(R.id.TextoReconocidoEt);

        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        ReconocerTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null){
                    Toast.makeText(ReconocerTexto.this, "Por favor eliga una imagen", Toast.LENGTH_SHORT).show();
                }else{
                    reconocerTextoDeImagen();
                }
            }
        });

    }

    private void reconocerTextoDeImagen() {

        try {
            InputImage inputImage = InputImage.fromFilePath(this, uri);
            Task<Text> textTask = textRecognizer.process(inputImage)
                    .addOnSuccessListener(new OnSuccessListener<Text>() {
                        @Override
                        public void onSuccess(Text text) {
                            String texto = text.getText();
                            TextoReconocidoEt.setText(texto);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ReconocerTexto.this, "No se pudo reconocer el texto debido a: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            Toast.makeText(this, "Error al preparar la imagen: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void AbrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galeriaARL.launch(intent);
    }

    private void AbrirCamara(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Titulo");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion");

        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        camaraARL.launch(intent);
    }


    private ActivityResultLauncher<Intent> galeriaARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        uri = data.getData();
                        image.setImageURI(uri);
                        TextoReconocidoEt.setText("");
                    }else {
                        Toast.makeText(ReconocerTexto.this, "Acción cancelada por el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> camaraARL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        image.setImageURI(uri);
                        TextoReconocidoEt.setText("");
                    }else{
                        Toast.makeText(ReconocerTexto.this, "Acción cancelada por el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Menu_abrir_galeria){
            AbrirGaleria();
            //Toast.makeText(this, "Abrir galeria", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.Menu_abrir_camara){
            AbrirCamara();
            //Toast.makeText(this, "Abrir camara", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}