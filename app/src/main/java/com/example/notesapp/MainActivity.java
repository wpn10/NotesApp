package com.example.notesapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.notesapp.Activity.InsertNotesActivity;
import com.example.notesapp.Activity.UpdateNotesActivity;
import com.example.notesapp.Adapter.NotesAdapter;
import com.example.notesapp.Model.Notes;
import com.example.notesapp.ViewModel.NotesViewModel;
import com.example.notesapp.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    ActivityUpdateNotesBinding binding;
    int sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecycler = findViewById(R.id.notesRecycler);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        sid = getIntent().getIntExtra("id", 0);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
        notesViewModel.getallNotes.observe(this, notes -> {
            notesRecycler.setLayoutManager(new GridLayoutManager(this,2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            notesRecycler.setAdapter(adapter);
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(notesRecycler);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped (@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int iid = viewHolder.getAdapterPosition();
            adapter.notifyItemRemoved(iid);
        }
    };
}