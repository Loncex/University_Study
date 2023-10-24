package com.example.app_universidad3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class AgregarNotas extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageButton Btn_saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        titleEditText = findViewById(R.id.notes_title);
        contentEditText = findViewById(R.id.notes_content);
        Btn_saveNote = findViewById(R.id.Btn_save_note);

        Btn_saveNote.setOnClickListener((v)-> saveNote());

    }

    void saveNote(){
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if(noteTitle == null || noteTitle.isEmpty() ){
            titleEditText.setError("Necesita ingresar el titulo");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);

    }

    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionreferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //notas fueron agregadas
                    Utility.showToast(AgregarNotas.this, "Nota Agregada Correctamente");
                    finish();
                }else{
                    Utility.showToast(AgregarNotas.this, "No se pudo agregar la nota :(");
                }
            }
        });

    }

}