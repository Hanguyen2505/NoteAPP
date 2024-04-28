package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesapp.databinding.ActivityNoteBinding;


public class NoteActivity extends AppCompatActivity {

    ActivityNoteBinding binding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidget();
//        loadFromDBToMemory();
        setNoteAdapter();
        clickNote();

        binding.createNoteTab.setOnClickListener(v -> newNote());
    }


    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void initWidget() {
        setSupportActionBar(binding.toolbar.getRoot());
    }

    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.noteArrayList);
        binding.listView.setAdapter(noteAdapter);

    }

    private void clickNote() {
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = (Note) binding.listView.getItemAtPosition(position);
                Intent intent = new Intent(NoteActivity.this, TakeNoteActivity.class);
                startActivity(intent);
                intent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getId());
            }
        });
    }

    public void newNote() {
        Intent createNewNote = new Intent(NoteActivity.this, TakeNoteActivity.class);
        startActivity(createNewNote);
        Log.d("completed", "okay");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int r = item.getItemId();
        if (r == R.id.logout) {
            finish();
            startActivity(new Intent(NoteActivity.this, MainActivity.class));
        } else if (r == R.id.add_tab) {
            startActivity(new Intent(NoteActivity.this, TakeNoteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}