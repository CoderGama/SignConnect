package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3G extends AppCompatActivity {

    private Button btnLearn, btnGame1, btnGame2, btnGame3,back;
    ImageView i1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_g);

        btnLearn = findViewById(R.id.btnLearn);
        btnGame1 = findViewById(R.id.btnGame1);
        btnGame2 = findViewById(R.id.btnGame2);
        btnGame3 = findViewById(R.id.btnGame3);
        back=findViewById(R.id.btnBack);
        i1=findViewById(R.id.circularImageView1);
        i1.setImageResource(R.drawable.b);



        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3G.this, "Learn Selected", Toast.LENGTH_SHORT).show();
                // Navigate to Learn Activity
                Intent intent = new Intent(MainActivity3G.this, MainActivity8G.class);
                startActivity(intent);
                finish();
            }
        });

        btnGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity3.this, "Game 1 Selected", Toast.LENGTH_SHORT).show();
                // Navigate to Game 1 Activity
                Intent intent1 = new Intent(MainActivity3G.this, MainActivity4G.class);
                startActivity(intent1);
                finish();
            }
        });

        btnGame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3G.this, "Game 2 Selected", Toast.LENGTH_SHORT).show();
                // Navigate to Game 2 Activity
                Intent intent4 = new Intent(MainActivity3G.this, MainActivity7G.class);
                startActivity(intent4);
                finish();
            }
        });

        btnGame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity3.this, "Game 3 Selected", Toast.LENGTH_SHORT).show();
                // Navigate to Game 3 Activity
                //Intent intent2 = new Intent(MainActivity3G.this, MainActivity6.class);
                //startActivity(intent2);
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(MainActivity3G.this, MainActivity2.class);
                startActivity(ii);
                finish();
            }
        });
    }
}
