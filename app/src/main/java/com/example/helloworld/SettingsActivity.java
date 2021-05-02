package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class SettingsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView whichSettingsToShow = findViewById(R.id.whichSettings);
        String whichButtonPressed = getIntent().getStringExtra("which_button_pressed");
        if (whichButtonPressed.equals("singlePlayerButtonPressed")) {
            whichSettingsToShow.setText("Show single player settings here");
        } else if (whichButtonPressed.equals("multiPlayerButtonPressed")) {
            whichSettingsToShow.setText("Show multi player settings here");
        }

        /*
        Fragment fragment;
        String whichButtonPressed = getIntent().getStringExtra("which_button_pressed");

        if (whichButtonPressed.equals("singlePlayerButtonPressed")) {
            fragment = new SinglePlayerSettingsFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();
        } else if (whichButtonPressed.equals("multiPlayerButtonPressed")) {

        }*/
    }
}