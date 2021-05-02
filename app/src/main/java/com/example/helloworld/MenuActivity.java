package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private void changeActivity(String whichButton) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("which_button_pressed", whichButton);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button singlePlayerButton = findViewById(R.id.btnSinglePlayer);
        Button multiPlayerButton = findViewById(R.id.btnMultiPlayer);

        singlePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity("singlePlayerButtonPressed");
            }
        });

        multiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity("multiPlayerButtonPressed");
            }
        });

    }
}