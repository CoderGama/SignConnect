package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity6 extends AppCompatActivity {

    private ImageView[] imageViews = new ImageView[4];
    private Button[] buttons = new Button[4];
    private TextView timerTextView;
    private int score = 0, matches = 0;
    private long startTime;
    private Handler timerHandler;
    private boolean[] isAnswered = {false, false, false, false};

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = SystemClock.elapsedRealtime() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("Timer: %d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        imageViews[0] = findViewById(R.id.image1);
        imageViews[1] = findViewById(R.id.image2);
        imageViews[2] = findViewById(R.id.image3);
        imageViews[3] = findViewById(R.id.image4);

        buttons[0] = findViewById(R.id.buttonA);
        buttons[1] = findViewById(R.id.buttonB);
        buttons[2] = findViewById(R.id.buttonC);
        buttons[3] = findViewById(R.id.buttonD);

        timerTextView = findViewById(R.id.timerTextView);

        timerHandler = new Handler();
        startTime = SystemClock.elapsedRealtime();
        timerHandler.postDelayed(timerRunnable, 0);

        setButtonListeners();
    }

    private void setButtonListeners() {
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(view -> checkMatch(index));
        }
    }

    private void checkMatch(int buttonIndex) {
        int correctIndex = -1;

        // Mapping of buttons to correct image indexes
        switch (buttonIndex) {
            case 0:
                correctIndex = 2; // Button A matches Image 3
                break;
            case 1:
                correctIndex = 3; // Button B matches Image 4
                break;
            case 2:
                correctIndex = 0; // Button C matches Image 1
                break;
            case 3:
                correctIndex = 1; // Button D matches Image 2
                break;
        }

        if (!isAnswered[buttonIndex]) {
            if (correctIndex == buttonIndex) {
                buttons[buttonIndex].setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                score++;
                matches++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                buttons[buttonIndex].setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            }

            isAnswered[buttonIndex] = true;

            if (matches == imageViews.length) {
                endGame();
            } else {
                resetButtons(); // Reset button colors for the next round
            }
        }
    }

    private void resetButtons() {
        for (Button button : buttons) {
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    private void endGame() {
        timerHandler.removeCallbacks(timerRunnable);
        long timeTaken = SystemClock.elapsedRealtime() - startTime;

        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int attempts2 = prefs.getInt("Game2Attempts", 0);
        attempts2++;
        editor.putInt("Game2Attempts", attempts2);
        editor.apply();

        Intent intent = new Intent(MainActivity6.this, MainActivity5.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("ATTEMPTS", attempts2);
        intent.putExtra("TIME_TAKEN", timeTaken);
        startActivity(intent);
        finish();
    }
}
