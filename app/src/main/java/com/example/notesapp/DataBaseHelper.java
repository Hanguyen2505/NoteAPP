package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public  static final String databaseName = "Signup.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users( email TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }

    public Boolean insertData(String email, String password) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        long result = userDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        Cursor cursor = userDatabase.rawQuery("SELECT * FROM users where email = ?", new String[]{email});

        if (cursor.getCount() > 0) { // return the number of rows                                                                                                                       
            return  true;
        } else {
            return false;
        }

    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase userDatabase = this.getWritableDatabase();
        Cursor cursor = userDatabase.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return  true;
        } else {
            return false;
        }
    }
}
