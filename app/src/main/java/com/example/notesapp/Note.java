package com.example.notesapp;
import java.util.ArrayList;
import java.util.Date;

public class Note {
    protected static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "note_edit";
    private int id;
    private String title;
    private String content;
    private Date deleted;

    public Note(int id, String title, String content, Date deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
    }

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deleted = null;
    }

    public static Note getNoteForExtra(int passNoteID) {
        for (Note note : noteArrayList) {
            return note;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
