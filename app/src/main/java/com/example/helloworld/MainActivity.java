package com.example.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // This is a comment here.

    boolean isPlayer1turn = true;
    boolean filled11 = false;
    boolean filled12 = false;
    boolean filled13 = false;
    boolean filled21 = false;
    boolean filled22 = false;
    boolean filled23 = false;
    boolean filled31 = false;
    boolean filled32 = false;
    boolean filled33 = false;

    boolean someoneWon = false;

    int[][] arr = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

    public boolean allFilled() {
        if (filled11&&filled12&&filled13&&filled21&&filled22&&filled23&&filled31&&filled32&&filled33) {
            return true;
        } else {
            return false;
        }
    }

    public void printArray() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.println(arr[i][j]);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void checkWinner() {

        TextView whoseTurn = findViewById(R.id.turnTextView);

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

        int count, count2;
        for(int i = 0; i < 8; ++i) {
            count = 0; count2 = 0;
            for (int j = 0; j < 3; ++j) {
                if (cross[j] == check[i][j]) {
                    count += 1;
                }
            }
            for (int j = 0; j < 3; ++j) {
                if (circle[j] == check[i][j]) {
                    count2 += 1;
                }
            }
            if (count == 3) {
                whoseTurn.setText("Player 2 Won, congrats :)");
                someoneWon = true;
                break;
            }
            if (count2 == 3) {
                whoseTurn.setText("Player 1 Won, congrats :)");
                someoneWon = true;
                break;
            }
        }
        if (!someoneWon) {
            printArray();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView whoseTurn = findViewById(R.id.turnTextView);
        whoseTurn.setText("Turn: Player 1 (Circle)");
    }

    @SuppressLint("SetTextI18n")
    public void click11(View view) {
        Log.i("info", "click11");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled11 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle11);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled11 = true;
                arr[0][0] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross11);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled11 = true;
                arr[0][0] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }

    }

    @SuppressLint("SetTextI18n")
    public void click12(View view) {
        Log.i("info", "click12");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled12 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle12);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled12 = true;
                arr[0][1] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross12);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled12 = true;
                arr[0][1] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click13(View view) {
        Log.i("info", "click13");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled13 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle13);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled13 = true;
                arr[0][2] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross13);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled13 = true;
                arr[0][2] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click21(View view) {
        Log.i("info", "click21");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled21 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle21);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled21 = true;
                arr[1][0] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross21);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled21 = true;
                arr[1][0] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click22(View view) {
        Log.i("info", "click22");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled22 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle22);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled22 = true;
                arr[1][1] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross22);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled22 = true;
                arr[1][1] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click23(View view) {
        Log.i("info", "click23");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled23 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle23);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled23 = true;
                arr[1][2] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross23);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled23 = true;
                arr[1][2] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click31(View view) {
        Log.i("info", "click31");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled31 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle31);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled31 = true;
                arr[2][0] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross31);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled31 = true;
                arr[2][0] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click32(View view) {
        Log.i("info", "click32");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled32 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle32);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled32 = true;
                arr[2][1] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross32);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled32 = true;
                arr[2][1] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

    @SuppressLint("SetTextI18n")
    public void click33(View view) {
        Log.i("info", "click33");
        TextView whoseTurn = findViewById(R.id.turnTextView);

        if (!filled33 && !someoneWon) {
            if (isPlayer1turn) {
                ImageView circle = findViewById(R.id.circle33);
                circle.animate().alpha(1).setDuration(300);
                isPlayer1turn = false;
                filled33 = true;
                arr[2][2] = 0;
                whoseTurn.setText("Turn: Player 2 (Cross)");
            } else {
                ImageView cross = findViewById(R.id.cross33);
                cross.animate().alpha(1).setDuration(300);
                isPlayer1turn = true;
                filled33 = true;
                arr[2][2] = 1;
                whoseTurn.setText("Turn: Player 1 (Circle)");
            }
            checkWinner();
        }
    }

}

