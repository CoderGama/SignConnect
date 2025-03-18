package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity7 extends AppCompatActivity {

    private ImageView image1, image2, image3, image4,I1;
    private static final String PREFS_NAME = "GamePrefs";
    private static final String ATTEMPTS_KEY = "Game3Attempts";
    private Button buttonA, buttonB, buttonC, buttonD;
    private TextView timerTextView;
    private int score = 0;
    private Custom c;
    private long startTime, timeTaken;
    private boolean[] isAnswered = {false, false, false, false};
    private Handler timerHandler;
    private int selectedImageIndex = -1;
    private View selectedImageView, selectedButtonView;// To track the selected image index

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = SystemClock.elapsedRealtime() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText("Timer:\n" + String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(timerRunnable, 500);
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        I1=findViewById(R.id.newImageView);
        c=findViewById(R.id.lineView);
        timerTextView = findViewById(R.id.timerTextView1);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7.this);

                // Set the title for the dialog
                builder.setTitle("How to Play?");

                // Set the content of the dialog, you can add any text or view
                builder.setMessage("• Click on a photo from the left side to select it.\n" +
                        "• Then, choose the corresponding matching answer from the right side.\n" +
                        "• Each correct match will earn you 1 point.\n" +
                        "• You have only four attempts, so choose carefully.\n" +
                        "• If you select the wrong answer or try to match multiple answers for a single photo, you will lose valuable chances.\n" +
                        "• Aim to match all photos correctly within the given attempts!");

                // Add an OK button to dismiss the dialog
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();  // Dismiss the dialog
                    }
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        timerHandler = new Handler(); // Initialize the Handler
        startTime = SystemClock.elapsedRealtime();
        timerHandler.postDelayed(timerRunnable, 0); // Start the timer

        setImageListeners();
        setButtonListeners();
    }

    private void setImageListeners() {
        image1.setOnClickListener(view -> selectImage(0, image1));
        image2.setOnClickListener(view -> selectImage(1, image2));
        image3.setOnClickListener(view -> selectImage(2, image3));
        image4.setOnClickListener(view -> selectImage(3, image4));
    }

    private void setButtonListeners() {
        buttonA.setOnClickListener(view -> checkAnswer('F',buttonA));
        buttonB.setOnClickListener(view -> checkAnswer('C',buttonB));
        buttonC.setOnClickListener(view -> checkAnswer('U',buttonC));
        buttonD.setOnClickListener(view -> checkAnswer('I',buttonD));
    }

    private void selectImage(int index, ImageView imageView) {
        if (isAnswered[index]) return;  // Don't allow selecting already answered images
        selectedImageIndex = index;
        selectedImageView = imageView;
        enableButtons();  // Enable buttons only after an image is selected
    }



    private void checkAnswer(char selectedLetter, Button selectedButton) {
        if (selectedImageIndex == -1) return;  // No image selected

        char correctLetter = 'A'; // Default, change this based on the selectedImageIndex
        switch (selectedImageIndex) {
            case 0:
                correctLetter = 'U';
                break;
            case 1:
                correctLetter = 'I';
                break;
            case 2:
                correctLetter = 'F';
                break;
            case 3:
                correctLetter = 'C';
                break;
        }

        // Get the selected image and button
        ImageView selectedImageView = findViewById(getResources().getIdentifier("image" + (selectedImageIndex + 1), "id", getPackageName()));
        int buttonIndex = getResources().getIdentifier("button" + (char) ('A' + Arrays.asList(buttonA, buttonB, buttonC, buttonD).indexOf(selectedButton)), "id", getPackageName());
        Button selectedButtonView = findViewById(buttonIndex);

        // Calculate the coordinates of the selected image and button
        int[] imageLocation = new int[2];
        selectedImageView.getLocationOnScreen(imageLocation);
        float imageX = imageLocation[0] + selectedImageView.getWidth() / 2f;
        float imageY = imageLocation[1] + selectedImageView.getHeight() / 2f;

        int[] buttonLocation = new int[2];
        selectedButtonView.getLocationOnScreen(buttonLocation);
        float buttonX = buttonLocation[0] + selectedButtonView.getWidth() / 2f;
        float buttonY = buttonLocation[1] + selectedButtonView.getHeight() / 2f;

        // Update the LineView to draw the line
        c.setLinePositions(imageX, imageY, buttonX, buttonY);

        // Check if the selected letter is correct
        if (selectedLetter == correctLetter) {
            score++;
            int lightGreenColor = Color.parseColor("#20da0e");
            selectedButton.setBackgroundColor(lightGreenColor);
        } else {
            int lightRedColor = Color.parseColor("#f41616");
            selectedButton.setBackgroundColor(lightRedColor);
        }

        isAnswered[selectedImageIndex] = true;
        disableButtons();
        selectedImageIndex = -1;  // Reset selected image

        // Check if all images are answered
        boolean allAnswered = true;
        for (boolean answered : isAnswered) {
            if (!answered) {
                allAnswered = false;
                break;
            }
        }
        if (allAnswered) {
            endGame();
        }
    }

    private void disableButtons() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    private void enableButtons() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);

        // Reset the button background colors to their original color
        buttonA.setBackgroundColor(Color.parseColor("#E5E5E5"));
        buttonB.setBackgroundColor(Color.parseColor("#E5E5E5"));
        buttonC.setBackgroundColor(Color.parseColor("#E5E5E5"));
        buttonD.setBackgroundColor(Color.parseColor("#E5E5E5"));
    }

    private void endGame() {
        timerHandler.removeCallbacks(timerRunnable);
        timeTaken = SystemClock.elapsedRealtime() - startTime;

        // Increment attempt count
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int attempts1 = prefs.getInt(ATTEMPTS_KEY, 0);
        attempts1++;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ATTEMPTS_KEY, attempts1);
        editor.apply();

        // End the game and show the final score
        Intent intent = new Intent(MainActivity7.this, MainActivity5.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("ATTEMPTS", attempts1);
        intent.putExtra("Accuracy",4);
        intent.putExtra("TIME_TAKEN", timeTaken);
        startActivity(intent);
        finish();
    }
}
