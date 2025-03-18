package com.example.signconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.SpeechRecognizer.*;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity_Text_to_Speech extends AppCompatActivity {

    private HashMap<Character, Integer> signLanguageMap;
    private EditText inputEditText;
    private LinearLayout imageContainer;
    private int imageWidth; // Width of each image
    private int imageHeight; // Height of each image
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_text_to_speech);

        // Initialize views
        inputEditText = findViewById(R.id.inputEditText);
        imageContainer = findViewById(R.id.imageContainer);
        Button displayButton = findViewById(R.id.displayButton);
        Button speechButton = findViewById(R.id.speechButton);

        // Populate sign language map
        populateSignLanguageMap();

        // Set up button click listener for displayButton
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputEditText.getText().toString().toUpperCase();
                if (text.isEmpty()) {
                    Toast.makeText(MainActivity_Text_to_Speech.this, "Please enter some text", Toast.LENGTH_SHORT).show();
                } else {
                    displaySignLanguage(text);
                }
            }
        });

        // Set up button click listener for speechButton
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechRecognition();
            }
        });
    }

    private void populateSignLanguageMap() {
        signLanguageMap = new HashMap<>();
        signLanguageMap.put('A', R.drawable.ea);
        signLanguageMap.put('B', R.drawable.eb);
        signLanguageMap.put('C', R.drawable.ec);
        signLanguageMap.put('D', R.drawable.ed);
        signLanguageMap.put('E', R.drawable.ee);
        signLanguageMap.put('F', R.drawable.ef);
        signLanguageMap.put('G', R.drawable.eg);
        signLanguageMap.put('H', R.drawable.eh);
        signLanguageMap.put('I', R.drawable.ei);
        signLanguageMap.put('J', R.drawable.ej);
        signLanguageMap.put('K', R.drawable.ek);
        signLanguageMap.put('L', R.drawable.el);
        signLanguageMap.put('M', R.drawable.em);
        signLanguageMap.put('N', R.drawable.en);
        signLanguageMap.put('O', R.drawable.eo);
        signLanguageMap.put('P', R.drawable.ep);
        signLanguageMap.put('Q', R.drawable.eq);
        signLanguageMap.put('R', R.drawable.er);
        signLanguageMap.put('S', R.drawable.es);
        signLanguageMap.put('T', R.drawable.et);
        signLanguageMap.put('U', R.drawable.eu);
        signLanguageMap.put('V', R.drawable.ev);
        signLanguageMap.put('W', R.drawable.ew);
        signLanguageMap.put('X', R.drawable.ex);
        signLanguageMap.put('Y', R.drawable.ey);
        signLanguageMap.put('Z', R.drawable.ez);
    }

    private void displaySignLanguage(String text) {
        imageContainer.removeAllViews();

        // Split the text by spaces to handle each word separately
        String[] words = text.split("\\s+");

        // Determine the maximum length of words
        int maxWordLength = 0;
        for (String word : words) {
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
        }

        // Determine the number of words
        int numberOfWords = words.length;

        // Calculate image dimensions based on the maximum word length and number of words
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        imageWidth = screenWidth / maxWordLength;
        imageHeight = (int) (imageWidth * 1.5); // Adjust height as needed

        // Create and add horizontal scroll views for each word
        for (String word : words) {
            // Create a new HorizontalScrollView for this word
            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
            horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Create a new LinearLayout to hold the images for this word
            LinearLayout wordLayout = new LinearLayout(this);
            wordLayout.setOrientation(LinearLayout.HORIZONTAL);
            wordLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            wordLayout.setPadding(0, 16, 0, 16); // Adjust top and bottom padding as needed

            // Add images for each letter in the word
            for (char letter : word.toCharArray()) {
                if (signLanguageMap.containsKey(letter)) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(signLanguageMap.get(letter));
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(
                            imageWidth,
                            imageHeight // Adjusted height
                    ));
                    wordLayout.addView(imageView);
                }
            }

            // Add the horizontal layout for this word to the HorizontalScrollView
            horizontalScrollView.addView(wordLayout);

            // Add the HorizontalScrollView for this word to the main container
            imageContainer.addView(horizontalScrollView);

            // Create and add a TextView to show the word
            TextView wordTextView = new TextView(this);
            wordTextView.setText(word);
            wordTextView.setTextSize(18); // Adjust text size as needed
            wordTextView.setTextColor(getResources().getColor(android.R.color.white)); // Adjust text color as needed
            wordTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            wordTextView.setPadding(0, 8, 0, 16); // Padding below the images

            imageContainer.addView(wordTextView);
        }
    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
        startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String spokenText = result.get(0).toUpperCase();
                inputEditText.setText(spokenText);
                displaySignLanguage(spokenText);
            }
        }
    }
}