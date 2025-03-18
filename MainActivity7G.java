package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity7G extends AppCompatActivity {

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
        setContentView(R.layout.activity_main_activity7_g);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity7G.this);

                // Set the title for the dialog
                builder.setTitle("કેવી રીતે રમવું?");

                // Set the content of the dialog, you can add any text or view
                builder.setMessage("• પસંદ કરવા માટે ડાબી બાજુથી ફોટો પર ક્લિક કરો.\n" +
                        "• પછી, જમણી બાજુથી અનુરૂપ મેળ ખાતો જવાબ પસંદ કરો.\n" +
                        "• દરેક સાચો મેચ તમને 1 પોઈન્ટ મેળવશે.\n" +
                        "• તમારી પાસે માત્ર ચાર પ્રયાસો છે, તેથી કાળજીપૂર્વક પસંદ કરો.\n" +
                        "• જો તમે ખોટો જવાબ પસંદ કરો છો અથવા એક ફોટો માટે બહુવિધ જવાબો સાથે મેળ કરવાનો પ્રયાસ કરો છો, તો તમે મૂલ્યવાન તકો ગુમાવશો.\n" +
                        "• આપેલ પ્રયાસોમાં બધા ફોટાને યોગ્ય રીતે મેચ કરવાનું લક્ષ્ય રાખો!");

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
        buttonA.setOnClickListener(view -> checkAnswer("ક્ષ",buttonA));
        buttonB.setOnClickListener(view -> checkAnswer("ભ",buttonB));
        buttonC.setOnClickListener(view -> checkAnswer("ફ",buttonC));
        buttonD.setOnClickListener(view -> checkAnswer("ઙ",buttonD));
    }

    private void selectImage(int index, ImageView imageView) {
        if (isAnswered[index]) return;  // Don't allow selecting already answered images
        selectedImageIndex = index;
        selectedImageView = imageView;
        enableButtons();  // Enable buttons only after an image is selected
    }



    private void checkAnswer(String selectedLetter, Button selectedButton) {
        if (selectedImageIndex == -1) return;  // No image selected

        String correctLetter = "hi"; // Default, change this based on the selectedImageIndex
        switch (selectedImageIndex) {
            case 0:
                correctLetter ="ફ";
                break;
            case 1:
                correctLetter = "ક્ષ";
                break;
            case 2:
                correctLetter = "ઙ";
                break;
            case 3:
                correctLetter = "ભ";
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
        Intent intent = new Intent(MainActivity7G.this, MainActivity5G.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("ATTEMPTS", attempts1);
        intent.putExtra("Accuracy",4);
        intent.putExtra("TIME_TAKEN", timeTaken);
        startActivity(intent);
        finish();
    }
}
