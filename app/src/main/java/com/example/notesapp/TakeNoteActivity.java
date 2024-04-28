package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.databinding.ActivityMainBinding;
import com.example.notesapp.databinding.ActivityTakeNoteBinding;

import java.util.Objects;


public class TakeNoteActivity extends AppCompatActivity {

    ActivityTakeNoteBinding binding;
    private Note selectedNote;
//    EditText email = findViewById(R.id.email);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTakeNoteBinding.inflate(getLayoutInflater());
//        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidget();
        checkForEditNote();

    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();
        int passNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getNoteForExtra(passNoteID);

        if (selectedNote == null) {
            binding.noteTitle.setText(selectedNote.getTitle());
            binding.noteContent.setText(selectedNote.getContent());
        }
    }

    private void saveNote() {
//        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

//        NoteManager noteManager = NoteManager.instanceOfDatabase(this);
        String getTitle = String.valueOf(binding.noteTitle.getText());
        String getContent = String.valueOf(binding.noteContent.getText());
//        String userID = String.valueOf(email.getText());
        if (selectedNote == null) {
            int id = Note.noteArrayList.size(); //add as newest View so the ID == size
            Note newNote = new Note(id, getTitle, getContent);
            Note.noteArrayList.add(newNote);
//        sqLiteManager.addNoteToDatabase(newNote);
        }
        else {
            selectedNote.setTitle(getTitle);
            selectedNote.setContent(getContent);
        }

//        noteManager.storeNote(email, getContent, getTitle);
        Toast.makeText(TakeNoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initWidget() {
        setSupportActionBar(binding.toolbar.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int r = item.getItemId();
        if (r == R.id.save) {
            saveNote();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_take_note, menu);
        return true;
    }


}