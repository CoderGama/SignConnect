package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

public class MainActivity5G extends AppCompatActivity {

    private TextView finalScoreTextView;
    private TextView attemptsTextView;
    private TextView timeTakenTextView;
    Button b1;
    TextView t1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5_g);

        finalScoreTextView = findViewById(R.id.finalScoreTextView);
        attemptsTextView = findViewById(R.id.attemptsTextView);
        timeTakenTextView = findViewById(R.id.timeTakenTextView);
        t1=findViewById(R.id.textView);
        b1=(Button) findViewById(R.id.button2);

        int score = getIntent().getIntExtra("SCORE", 0);
        int attempts = getIntent().getIntExtra("ATTEMPTS", 0);
        long timeTaken = getIntent().getLongExtra("TIME_TAKEN", 0);
        int Accuracy=getIntent().getIntExtra("Accuracy",0);

        finalScoreTextView.setText("તમારો સ્કોર:\n" + score);
        attemptsTextView.setText("પ્રયાસો:\n" + attempts);
        t1.setText("તમારી ચોકસાઈ:\n"+((score)*(100/Accuracy)));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeTaken);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeTaken) % 60;

        timeTakenTextView.setText("સમય લીધો:\n" + minutes + " mins, " + seconds + " secs");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity5G.this,MainActivity3G.class);
                startActivity(i1);
                finish();
            }
        });



    }
}
