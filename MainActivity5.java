package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

public class MainActivity5 extends AppCompatActivity {

    private TextView finalScoreTextView;
    private TextView attemptsTextView;
    private TextView timeTakenTextView;
    Button b1;
    TextView t1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        attemptsTextView = findViewById(R.id.attemptsTextView);
        timeTakenTextView = findViewById(R.id.timeTakenTextView);
        t1=findViewById(R.id.textView);
        b1=(Button) findViewById(R.id.button2);

        int score = getIntent().getIntExtra("SCORE", 0);
        int attempts = getIntent().getIntExtra("ATTEMPTS", 0);
        long timeTaken = getIntent().getLongExtra("TIME_TAKEN", 0);
        int Accuracy=getIntent().getIntExtra("Accuracy",0);

        finalScoreTextView.setText("Your Score:\n" + score);
        attemptsTextView.setText("Attempts:\n" + attempts);
        t1.setText("Your Accuracy:\n"+((score)*(100/Accuracy)));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeTaken);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeTaken) % 60;

        timeTakenTextView.setText("Time Taken:\n" + minutes + " mins, " + seconds + " secs");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity5.this,MainActivity3.class);
                startActivity(i1);
                finish();
            }
        });



    }
}
