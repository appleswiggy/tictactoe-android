package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    public int destroy_f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button mPlay = findViewById(R.id.btnplay);
        //Button mHighScore = findViewById(R.id.btnhighscore);
        //Button mOther;

        try {
            String message = getIntent().getStringExtra("cont_message");
            if (message.equals("Continue")){
                mPlay.setText(R.string.Continue);
                destroy_f = 1;
                Log.i("continue", "ifblock in");
            }
        }
        catch (Exception e){
            Log.i("continue","catchblock in");
        }
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change activity
                if (destroy_f == 1){
                    //Continue feature
                    MenuActivity.this.finish();
                }
                else {
                    //First time app start
                    Intent gameplayFull_intent = new Intent(MenuActivity.this, gameplayActivityFull.class);
                    startActivity(gameplayFull_intent);
                }
            }
        });
        //More buttons

        //Listeners

    }
}