package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

//    public void startGameplayActivity(String data) {
//        Intent intent = new Intent(this, gameplayActivity.class);
//        intent.putExtra("settings_data", data);
//        startActivity(intent);
//    }
//
//    public void startButtonSinglePlayer(View view) {
//        String settingsData = "some single player settings data";
//        startGameplayActivity(settingsData);
//    }
//
//    public void startButtonMultiPlayer(View view) {
//        String settingsData = "some multi player settings data";
//        startGameplayActivity(settingsData);
//    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        TextView whichSettingsToShow = findViewById(R.id.whichSettings);
//        String whichButtonPressed = getIntent().getStringExtra("which_button_pressed");
//        if (whichButtonPressed.equals("singlePlayerButtonPressed")) {
//            whichSettingsToShow.setText("Show single player settings here");
//        } else if (whichButtonPressed.equals("multiPlayerButtonPressed")) {
//            whichSettingsToShow.setText("Show multi player settings here");
//        }

//        Button startButton;
//
       Fragment fragment;
        String whichButtonPressed = getIntent().getStringExtra("which_button_pressed");
        if (whichButtonPressed.equals("singlePlayerButtonPressed")) {

            fragment = new SinglePlayerSettingsFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();

//            startButton = findViewById(R.id.startButtonSinglePlayer);
//            startButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String settingsData = "some single player settings data";
//                    startGameplayActivity(settingsData);
//                }
//            });

        }
        if (whichButtonPressed.equals("multiPlayerButtonPressed")) {

            fragment = new MultiPlayerSettingsFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();

//            startButton = findViewById(R.id.startButtonMultiPlayer);
//            startButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String settingsData = "some multi player settings data";
//                    startGameplayActivity(settingsData);
//                }
//            });

        }


    }
}