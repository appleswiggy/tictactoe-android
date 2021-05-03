package com.example.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class gameplayActivity extends AppCompatActivity {

    boolean isPlayer1turn, someoneWon, temp;

    String circlePlayerName, crossPlayerName;
    String firstTurn, secondTurn;

    boolean[] filledStatus = {false, false, false,  // filled11, filled12, filled13
                              false, false, false,  // filled21, filled22, filled23
                              false, false, false}; // filled31, filled32, filled33

    int[][] arr = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        String settingsData = getIntent().getStringExtra("settings_data");
        String[] arrOfStr = settingsData.split("@", 4);

//        Toast.makeText(this, arrOfStr[0] + arrOfStr[1] + arrOfStr[2] + arrOfStr[3], Toast.LENGTH_SHORT).show();

        if (arrOfStr[0].equals("m")) {
            circlePlayerName = arrOfStr[1];
            crossPlayerName = arrOfStr[2];
            if (arrOfStr[3].equals("Cross")) {
                temp = false;
                firstTurn = "Turn: " + crossPlayerName + " (Cross)";
                secondTurn = "Turn: " + circlePlayerName + " (Circle)";
            } else {
                temp = true;
                firstTurn = "Turn: " + circlePlayerName + " (Circle)";
                secondTurn = "Turn: " + crossPlayerName + " (Cross)";
            }
        } else if (arrOfStr[0].equals("s")) {
            if (arrOfStr[1].equals("Cross")) {
                temp = false;
                circlePlayerName = "Computer's turn";
                crossPlayerName = "Your turn";
                firstTurn = "Turn: Your turn (Cross)";

            } else if (arrOfStr[1].equals("Circle")) {
                temp = true;
                circlePlayerName = "Your turn";
                crossPlayerName = "Computer's turn";
                firstTurn = "Turn: Your turn (Circle)";
            }
        }

        setDefault();
    }

    public void cellPressed(View view) {
        ImageView cell = (ImageView) view;
        String position = cell.getTag().toString();

        int i = Character.getNumericValue(position.charAt(0));
        int j = Character.getNumericValue(position.charAt(1));

        Log.i("info", "click" + position);
        updateCell(i, j);
    }

    public void updateCell(int i, int j) {

        TextView whoseTurn = findViewById(R.id.turnTextView);
        if (!filledStatus[((i - 1) * 3) + (j - 1)] && !someoneWon) {
            if (isPlayer1turn) {
                int id = getResources().getIdentifier("circle" + i + j, "id", getPackageName());
                ImageView circle = findViewById(id);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filledStatus[((i - 1) * 3) + (j - 1)] = true;
                arr[i - 1][j - 1] = 0;
                whoseTurn.setText("Turn: " + crossPlayerName + " (Cross)");
            } else {
                int id = getResources().getIdentifier("cross" + i + j, "id", getPackageName());
                ImageView cross = findViewById(id);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filledStatus[((i - 1) * 3) + (j - 1)] = true;
                arr[i - 1][j - 1] = 1;
                whoseTurn.setText("Turn: " + circlePlayerName + " (Circle)");
            }
            checkWinner();
        }
    }

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

        whoseTurn.setText(firstTurn);
        playAgainButton.animate().alpha(0).setDuration(100);

        isPlayer1turn = temp;
        someoneWon = false;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                filledStatus[i * 3 + j] = false;
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

        for (int i = 0; i < 8; ++i) {

            if (Arrays.equals(check[i], cross)) {
                whoseTurn.setText(crossPlayerName + " Won, congrats :)");
                someoneWon = true;
                playAgainButton.animate().alpha(1).setDuration(300);
                break;
            }
            if (Arrays.equals(check[i], circle)) {
                whoseTurn.setText(circlePlayerName + " Won, congrats :)");
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
}
