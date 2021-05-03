package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MultiPlayerSettingsFragment extends Fragment {

    String settingsData = "some multi player settings data";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_multi_player_settings, container, false);

        Button startButton = (Button) v.findViewById(R.id.startButtonMultiPlayer);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), gameplayActivity.class);
                intent.putExtra("settings_data", settingsData);
                startActivity(intent);
            }
        });
        return v;
    }
}