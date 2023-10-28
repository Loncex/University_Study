package com.example.app_universidad3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class AgregarNotas extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageButton Btn_saveNote;
    TextView pageTitleTextView;
    String title, content, docId;
    boolean isEditMode = false;
    TextView deletenoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_notas);

        titleEditText = (EditText) findViewById(R.id.notes_title);
        contentEditText = (EditText) findViewById(R.id.notes_content);
        Btn_saveNote = (ImageButton) findViewById(R.id.Btn_save_note);
        pageTitleTextView = (TextView) findViewById(R.id.title_page);
        deletenoteTextView = (TextView) findViewById(R.id.Btn_delete_note);

        //data
        title = getIntent().getStringExtra("title");
        content= getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);

        if(isEditMode){
            pageTitleTextView.setText("Edita tu Apunte");
            deletenoteTextView.setVisibility(View.VISIBLE);
        }

        Btn_saveNote.setOnClickListener((v) -> saveNote());
        deletenoteTextView.setOnClickListener((v)-> deleteNoteFromFirebase());

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
        if(isEditMode){
            //actualizar apunte existente
            documentReference = Utility.getCollectionreferenceForNotes().document(docId);
        }else{
            //Crear nuevo apunte
            documentReference = Utility.getCollectionreferenceForNotes().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //las notas fueron agregadas
                    Utility.showToast(AgregarNotas.this, "Apunte Agregado Correctamente");
                    finish();
                }else{
                    Utility.showToast(AgregarNotas.this, "No se pudo agregar el apunte :(");
                }
            }
        });

    }

    private void deleteNoteFromFirebase(){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionreferenceForNotes().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //el apunte fue borrado
                    Utility.showToast(AgregarNotas.this, "Apunte eliminado correctamente");
                    finish();
                }else{
                    Utility.showToast(AgregarNotas.this, "No se pudo borrar el apunte :(");
                }
            }
        });

    }

}