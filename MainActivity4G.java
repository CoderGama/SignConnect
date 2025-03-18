package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.os.SystemClock;

import java.util.List;

public class MainActivity4G extends AppCompatActivity {

    private static final String PREFS_NAME = "GamePrefs";
    private static final String ATTEMPTS_KEY = "Game1Attempts";

    private ImageView questionImage,I1;
    private TextView timerTextView;
    TextView t1;
    private Button option1, option2, option3, option4;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private long startTime, timeTaken;

    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = SystemClock.elapsedRealtime() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText("સમય: "+String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    private Question[] questions = new Question[]{
            new Question(R.drawable.gpha, "ફ", "ઢ", "હ", "છ"),
            new Question(R.drawable.gshra, "ક્ષ", "મ", "જ", "જ્ઞ"),
            new Question(R.drawable.gbaa, "બ","ર" , "શ", "લ"),
            new Question(R.drawable.n2, "2", "3", "1", "5"),
            new Question(R.drawable.n9, "9", "8", "7", "6"),
            new Question(R.drawable.gchha, "છ", "ળ", "પ", "ણ"),
            new Question(R.drawable.n7, "7", "5", "8", "4"),
            new Question(R.drawable.gtaa, "થ", "ઝ", "વ", "ખ"),
            new Question(R.drawable.n6, "6", "9", "5", "8"),
            new Question(R.drawable.n8, "8", "6", "7", "3"),
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_g);

        questionImage = findViewById(R.id.imageView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        t1=(TextView) findViewById(R.id.textView2);
        timerTextView = findViewById(R.id.timerTextView);
        I1=findViewById(R.id.imageView2);
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4G.this);

                // Set the title for the dialog
                builder.setTitle("કેવી રીતે રમવું?");

                // Set the content of the dialog, you can add any text or view
                builder.setMessage("ઉપરોક્ત મીડિયાને અનુરૂપ સાચો વિકલ્પ પસંદ કરો.\n" +
                        "દરેક યોગ્ય જવાબ તમને +1 પોઈન્ટ આપે છે અને દરેક ખોટો જવાબ તમને 0 પોઈન્ટ આપે છે");

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

        startTime = SystemClock.elapsedRealtime();
        timerHandler.postDelayed(timerRunnable, 0);


        loadQuestion();

        View.OnClickListener answerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((Button) v);
            }
        };

        option1.setOnClickListener(answerListener);
        option2.setOnClickListener(answerListener);
        option3.setOnClickListener(answerListener);
        option4.setOnClickListener(answerListener);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];

            questionImage.setImageResource(currentQuestion.getImageResId());
            t1.setText((currentQuestionIndex+1)+"/10");


            List<String> shuffledOptions = currentQuestion.getOptions();

            option1.setText(shuffledOptions.get(0));
            option2.setText(shuffledOptions.get(1));
            option3.setText(shuffledOptions.get(2));
            option4.setText(shuffledOptions.get(3));
            resetButtonColors();
        } else {
            endGame();
        }
    }

    private void checkAnswer(Button selectedButton) {
        Question currentQuestion = questions[currentQuestionIndex];

        if (selectedButton.getText().equals(currentQuestion.getCorrectAnswer())) {
            int lightGreenColor = Color.parseColor("#20da0e");
            score++;
            selectedButton.setBackgroundColor(lightGreenColor);
            //Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            int lightRedColor = Color.parseColor("#f41616"); // Lighter red color
            selectedButton.setBackgroundColor(lightRedColor);
            //Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
            highlightCorrectAnswer();
        }


        currentQuestionIndex++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadQuestion();
            }
        }, 1000);
    }
    private void resetButtonColors() {
        option1.setBackgroundResource(R.drawable.fancy_button);
        option2.setBackgroundResource(R.drawable.fancy_button);
        option3.setBackgroundResource(R.drawable.fancy_button);
        option4.setBackgroundResource(R.drawable.fancy_button);
    }
    private void highlightCorrectAnswer() {
        Question currentQuestion = questions[currentQuestionIndex];
        int lightGreenColor = Color.parseColor("#20da0e"); // Lighter green color

        if (option1.getText().equals(currentQuestion.getCorrectAnswer())) {
            option1.setBackgroundColor(lightGreenColor);
        } else if (option2.getText().equals(currentQuestion.getCorrectAnswer())) {
            option2.setBackgroundColor(lightGreenColor);
        } else if (option3.getText().equals(currentQuestion.getCorrectAnswer())) {
            option3.setBackgroundColor(lightGreenColor);
        } else if (option4.getText().equals(currentQuestion.getCorrectAnswer())) {
            option4.setBackgroundColor(lightGreenColor);
        }
    }

    private void endGame() {

        timerHandler.removeCallbacks(timerRunnable);
        timeTaken = SystemClock.elapsedRealtime() - startTime;
        // Increment attempt count
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int attempts = prefs.getInt(ATTEMPTS_KEY, 0);
        attempts++;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ATTEMPTS_KEY, attempts);
        editor.apply();

        // End the game and show the final score
        Intent intent = new Intent(MainActivity4G.this, MainActivity5G.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("ATTEMPTS", attempts);
        intent.putExtra("TIME_TAKEN", timeTaken);
        intent.putExtra("Accuracy", 10);
        startActivity(intent);
        finish();
    }
}


