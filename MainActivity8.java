package com.example.signconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity8 extends AppCompatActivity {
    Button b1,b2,b3,b4;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        b1=findViewById(R.id.btnAlpha);
        b2=findViewById(R.id.btnNumbers);
        b3=findViewById(R.id.btnBack8);
        b4=findViewById(R.id.button);
        i1=findViewById(R.id.circularImageView8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity8.this, Learn.class);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity8.this, LearnN.class);
                startActivity(intent1);
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity8.this, MainActivity3.class);
                startActivity(intent2);
                finish();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(MainActivity8.this, MainActivity_Text_to_Speech.class);
                startActivity(intent3);
                finish();
            }
        });
        i1.setImageResource(R.drawable.b);
    }
}