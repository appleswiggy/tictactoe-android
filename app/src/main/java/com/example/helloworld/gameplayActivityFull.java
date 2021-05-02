package com.example.helloworld;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class gameplayActivityFull extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (AUTO_HIDE) {
                        delayedHide(AUTO_HIDE_DELAY_MILLIS);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    boolean isPlayer1turn, someoneWon;

    boolean[] filledStatus = {false, false, false,  // filled11, filled12, filled13
                              false, false, false,  // filled21, filled22, filled23
                              false, false, false}; // filled31, filled32, filled33

    int[][] arr = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

    public boolean allFilled() {
        int count = 0;
        for (int i = 0; i < 9; ++i) {
            if (filledStatus[i]) {
                count += 1;
            }
        }
        return count == 9;
    }

    @SuppressLint("SetTextI18n")
    public void setDefault() {
        TextView whoseTurn = findViewById(R.id.turnTextView);
        Button playAgainButton = findViewById(R.id.button);

        whoseTurn.setText("Turn: Player 1 (Circle)");
        playAgainButton.animate().alpha(0).setDuration(100);

        isPlayer1turn = true; someoneWon = false;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                filledStatus[i*3 + j] = false;
                arr[i][j] = -1;
            }
        }

        ImageView circle, cross;

        int circleId, crossId;
        for (int i = 1; i < 4; ++i) {   // This gets ids of all circles and crosses and turn their alpha to 0
            for (int j = 1; j < 4; ++j) {
                circleId = getResources().getIdentifier("circle" + i + j, "id", getPackageName());
                crossId = getResources().getIdentifier("cross" + i + j, "id", getPackageName());
                circle = findViewById(circleId);
                cross = findViewById(crossId);
                circle.animate().alpha(0).setDuration(100);
                cross.animate().alpha(0).setDuration(100);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void checkWinner() {

        TextView whoseTurn = findViewById(R.id.turnTextView);
        Button playAgainButton = findViewById(R.id.button);

        int[] cross = {1, 1, 1};
        int[] circle = {0, 0, 0};

        int[] arrRow1 = {arr[0][0], arr[0][1], arr[0][2]};
        int[] arrRow2 = {arr[1][0], arr[1][1], arr[1][2]};
        int[] arrRow3 = {arr[2][0], arr[2][1], arr[2][2]};

        int[] arrCol1 = {arr[0][0], arr[1][0], arr[2][0]};
        int[] arrCol2 = {arr[0][1], arr[1][1], arr[2][1]};
        int[] arrCol3 = {arr[0][2], arr[1][2], arr[2][2]};

        int[] arrDiag1 = {arr[0][0], arr[1][1], arr[2][2]};
        int[] arrDiag2 = {arr[0][2], arr[1][1], arr[2][0]};

        int[][] check = {arrRow1, arrRow2, arrRow3, arrCol1, arrCol2, arrCol3, arrDiag1, arrDiag2};

        for(int i = 0; i < 8; ++i) {

            if (Arrays.equals(check[i], cross)) {
                whoseTurn.setText("Player 2 Won, congrats :)");
                someoneWon = true;
                playAgainButton.animate().alpha(1).setDuration(300);
                break;
            }
            if (Arrays.equals(check[i], circle)) {
                whoseTurn.setText("Player 1 Won, congrats :)");
                someoneWon = true;
                playAgainButton.animate().alpha(1).setDuration(300);
                break;
            }
        }
        if (!someoneWon && allFilled()) {
            whoseTurn.setText("Its a tie :|");
            someoneWon = true;
            playAgainButton.animate().alpha(1).setDuration(300);
        }
    }

    public void playAgain(View view) {
        Log.i("info", "button pressed yess");
        Button button = findViewById(R.id.button);
        if (button.getAlpha() == 1) {
            setDefault();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gameplay_full);
        TextView whoseTurn = findViewById(R.id.turnTextView);
        whoseTurn.setText("Turn: Player 1 (Circle)");
        setDefault();
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.menu_button).setOnTouchListener(mDelayHideTouchListener);
        Button menu_btn = findViewById(R.id.menu_button);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu_intent = new Intent(gameplayActivityFull.this, MenuActivity.class);
                menu_intent.putExtra("cont_message" ,"Continue");
                startActivity(menu_intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void cellPressed(View view) {
        ImageView cell = (ImageView) view;
        String position = cell.getTag().toString();

        int i = Character.getNumericValue(position.charAt(0));
        int j = Character.getNumericValue(position.charAt(1));

        Log.i("info", "click" + position);

        TextView whoseTurn = findViewById(R.id.turnTextView);
        if (!filledStatus[((i-1)*3)+(j-1)] && !someoneWon) {
            if (isPlayer1turn) {
                int id = getResources().getIdentifier("circle" + i + j, "id", getPackageName());
                ImageView circle = findViewById(id);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filledStatus[((i-1)*3)+(j-1)] = true;
                arr[i-1][j-1] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                int id = getResources().getIdentifier("cross" + i + j, "id", getPackageName());
                ImageView cross = findViewById(id);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filledStatus[((i-1)*3)+(j-1)] = true;
                arr[i-1][j-1] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}