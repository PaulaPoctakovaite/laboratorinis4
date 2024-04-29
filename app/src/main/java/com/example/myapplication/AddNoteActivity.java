package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final EditText titleEditText = findViewById(R.id.title_edit_text);
        final EditText contentEditText = findViewById(R.id.content_edit_text);
        Button addNoteButton = findViewById(R.id.add_note_button);

        addNoteButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();

            if (title.trim().isEmpty() || content.trim().isEmpty()) {
                Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            Note newNote = new Note(title, content);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("note", newNote);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}