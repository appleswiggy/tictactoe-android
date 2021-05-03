package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SinglePlayerSettingsFragment extends Fragment {

    public SinglePlayerSettingsFragment(){}
    String settingsData, status = "Medium";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_single_player_settings, container, false);

        SeekBar seekBar = v.findViewById(R.id.seekBar);
        seekBar.setMax(2);
        seekBar.setProgress(1);

        TextView difficultyStatus = v.findViewById(R.id.difficultyStatus);
        difficultyStatus.setText("Difficulty: Medium");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar changed", "" + progress);
                if (progress == 0) {
                    status = "Easy";
                } else if (progress == 1) {
                    status = "Medium";
                } else {
                    status = "Hard";
                }
                difficultyStatus.setText("Difficulty: " + status);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button startButton = (Button) v.findViewById(R.id.startButtonSinglePlayer);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
                RadioButton radioButton;
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                settingsData = "s." + radioButton.getText().toString() + "." + status;


                Intent intent = new Intent(getContext(), gameplayActivity.class);
                intent.putExtra("settings_data", settingsData);
                startActivity(intent);
            }
        });

        return v;
    }


}