package com.example.signconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private Button btnEnglish, btnGujarati,back;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnEnglish = findViewById(R.id.btnEnglish);
        btnGujarati = findViewById(R.id.btnGujarati);
        i1=findViewById(R.id.circularImageView);
        back=findViewById(R.id.btnBack);
        i1.setImageResource(R.drawable.b);

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "English Selected", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(i1);
                finish();
            }
        });

        btnGujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Gujarati Selected", Toast.LENGTH_SHORT).show();
                Intent i2=new Intent(MainActivity2.this, MainActivity3G.class);
                startActivity(i2);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(ii);
                finish();
            }
        });
    }
}
