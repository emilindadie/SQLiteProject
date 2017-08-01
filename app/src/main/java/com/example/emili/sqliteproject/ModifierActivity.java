package com.example.emili.sqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ModifierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        Intent intent = getIntent();

        Toast.makeText(this, "L'id du contact est le "+String.valueOf(intent.getIntExtra("id", 0)), Toast.LENGTH_LONG).show();
    }
}
