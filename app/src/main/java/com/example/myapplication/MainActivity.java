package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Note> notes;
    public static NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        ListView listView = findViewById(R.id.notes_list_view);
        Button addNoteButton = findViewById(R.id.add_note_button);

        adapter = new NoteAdapter(this, notes);
        listView.setAdapter(adapter);

        ActivityResultContract<Void, Note> createNoteContract = new ActivityResultContract<Void, Note>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Void input) {
                return new Intent(context, AddNoteActivity.class);
            }

            @Override
            public Note parseResult(int resultCode, @Nullable Intent intent) {
                if (resultCode == Activity.RESULT_OK && intent != null) {
                    return (Note) intent.getSerializableExtra("note");
                } else {
                    return null;
                }
            }
        };

        ActivityResultLauncher<Void> createNoteLauncher = registerForActivityResult(createNoteContract, note -> {
            if (note != null) {
                notes.add(note);
                adapter.notifyDataSetChanged();
            }
        });

        addNoteButton.setOnClickListener(v -> createNoteLauncher.launch(null));
    }

    public static void removeNote(int position) {
        if (position >= 0 && position < notes.size()) {
            notes.remove(position);
        }
    }
}