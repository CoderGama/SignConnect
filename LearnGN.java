package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearnGN extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Button prevButton, nextButton,hm;

    private int currentIndex = 0;

    // Array of image resource IDs
    private int[] images = {
            R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4,
            R.drawable.n5, R.drawable.n6, R.drawable.n7, R.drawable.n8,
            R.drawable.n9
            // Add all 32 image resources here
    };

    // Array of image descriptions
    private String[] descriptions = {
            "1","2","3","4","5","6","7","8","9"
            // Add all 32 descriptions here
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_gn);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        hm=findViewById(R.id.home);

        updateContent();  // Set initial content

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateContent();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex < images.length - 1) {
                    currentIndex++;
                    updateContent();
                }
            }
        });
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnGN.this,MainActivity8G.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateContent() {
        imageView.setImageResource(images[currentIndex]);
        textView.setText(descriptions[currentIndex]);
    }
}
