package com.example.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.example.notesapp.Model.Notes;
import com.example.notesapp.R;
import com.example.notesapp.ViewModel.NotesViewModel;
import com.example.notesapp.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.doneNotesBtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();
            CreateNotes(title, subtitle, notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.getDateInstance().format(date.getTime());
        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesDates = sequence.toString();
        notesViewModel.insertNote(notes1);
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}