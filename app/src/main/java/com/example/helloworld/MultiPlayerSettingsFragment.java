package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MultiPlayerSettingsFragment extends Fragment {

    String settingsData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_multi_player_settings, container, false);

        EditText circlePlayerName = (EditText) v.findViewById(R.id.circlePlayerName);
        EditText crossPlayerName = (EditText) v.findViewById(R.id.crossPlayerName);

        Button startButton = (Button) v.findViewById(R.id.startButtonMultiPlayer);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup radioGroup2 = (RadioGroup) v.findViewById(R.id.radioGroup2);
                RadioButton radioButton2;
                int radioId2 = radioGroup2.getCheckedRadioButtonId();
                radioButton2 = v.findViewById(radioId2);
                String radio = radioButton2.getText().toString();


                settingsData = "m@" + circlePlayerName.getText().toString() + "@" +
                                crossPlayerName.getText().toString() + "@" + radio;

                Intent intent = new Intent(getContext(), gameplayActivity.class);
                intent.putExtra("settings_data", settingsData);
                startActivity(intent);
            }
        });
        return v;
    }
}