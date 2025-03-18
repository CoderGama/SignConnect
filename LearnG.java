package com.example.signconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearnG extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private Button prevButton, nextButton,hm;

    private int currentIndex = 0;

    // Array of image resource IDs
    private int[] images = {
            R.drawable.gkaa, R.drawable.gkha, R.drawable.ggaa,R.drawable.ggha,R.drawable.gada,R.drawable.gcha,
            R.drawable.gchha,R.drawable.gjaa,R.drawable.gzaa,R.drawable.gtaa,R.drawable.gtha,R.drawable.gdaa,
            R.drawable.gdda,R.drawable.ganna,R.drawable.gta,R.drawable.gthha,R.drawable.gda,R.drawable.gghha,
            R.drawable.gnaa,R.drawable.gpa,R.drawable.gpha,R.drawable.gbaa,R.drawable.gbha,R.drawable.gmaa,
            R.drawable.gyaa,R.drawable.graa,R.drawable.glaa,R.drawable.gvaa,R.drawable.gsaa,R.drawable.gsha,
            R.drawable.ghaa,R.drawable.gada,R.drawable.gshra,R.drawable.gyagna
            // Add all 32 image resources here
    };

    // Array of image descriptions
    private String[] descriptions = {
            "ક", "ખ", "ગ", "ઘ", "ઙ",
            "ચ", "છ", "જ", "ઝ",
            "ટ", "ઠ", "ડ", "ઢ", "ણ",
            "ત", "થ", "દ", "ધ", "ન",
            "પ", "ફ", "બ", "ભ", "મ",
            "ય", "ર", "લ", "વ", "શ",
            "સ", "હ", "ળ", "ક્ષ","જ્ઞ"
            // Add all 32 descriptions here
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_g);

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
                Intent intent=new Intent(LearnG.this,MainActivity8G.class);
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
