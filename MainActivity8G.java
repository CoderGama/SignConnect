package com.example.signconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity8G extends AppCompatActivity {
    Button b1,b2,b3;
    ImageView i1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity8_g);
        b1=findViewById(R.id.btnAlpha);
        b2=findViewById(R.id.btnNumbers);
        b3=findViewById(R.id.btnBack8);
        i1=findViewById(R.id.circularImageView8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity8G.this, LearnG.class);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity8G.this, LearnGN.class);
                startActivity(intent1);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity8G.this, MainActivity3G.class);
                startActivity(intent2);
                finish();
            }
        });
        i1.setImageResource(R.drawable.b);
    }
}