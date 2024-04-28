package com.example.notesapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteManager extends SQLiteOpenHelper {
    public static NoteManager noteManager;
    private static final String DATABASE_NAME = "my_notes.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_USER_ID = "user_id";

    public NoteManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String COLUMN_NOTE_TEXT = "note_text";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TIMESTAMP = "timestamp";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + COLUMN_USER_ID + " TEXT, "
                + COLUMN_NOTE_TEXT + " TEXT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                + " PRIMARY KEY ( COLUMN_TIMESTAMP),"
                + " FOREIGN KEY (COLUMN_USER_ID) REFERENCES email(users)"
                + ")";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    public static NoteManager instanceOfDatabase(Context context) {
        if (noteManager == null)
            noteManager = new NoteManager(context);
        return noteManager;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storeNote(String userId, String noteText, String title) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteManager.COLUMN_USER_ID, userId);
        values.put(NoteManager.COLUMN_NOTE_TEXT, noteText);
        if (title != null) {
            values.put(NoteManager.COLUMN_TITLE, title);
        }
        db.insert(NoteManager.TABLE_NOTES, null, values);
        db.close();
    }
}
