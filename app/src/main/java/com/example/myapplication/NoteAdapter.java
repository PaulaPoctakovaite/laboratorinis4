package com.example.myapplication;
import static com.example.myapplication.MainActivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }

        TextView noteText = convertView.findViewById(R.id.note_text);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        noteText.setText(note.getTitle() + "\n" + note.getContent());

        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DeleteNoteActivity.class);
            intent.putExtra("note_to_delete", note);
            getContext().startActivity(intent);
        });

        return convertView;
    }
}

