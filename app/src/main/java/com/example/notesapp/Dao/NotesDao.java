package com.example.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesapp.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {
    @Query("SELECT * FROM Notes_database")
    LiveData<List<Notes>> getallNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
