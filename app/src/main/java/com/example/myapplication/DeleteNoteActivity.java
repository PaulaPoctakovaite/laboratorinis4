package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DeleteNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra("note_position", -1);
        if (position != -1) {
            MainActivity.removeNote(position);
        }

        setResult(RESULT_OK);
        finish();
    }
}