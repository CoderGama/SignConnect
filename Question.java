package com.example.signconnect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    private int imageResId;
    private List<String> options;
    private String correctAnswer;

    public Question(int imageResId, String correctAnswer, String option2, String option3, String option4) {
        this.imageResId = imageResId;
        this.correctAnswer = correctAnswer;
        this.options = Arrays.asList(correctAnswer, option2, option3, option4);
        Collections.shuffle(this.options); // Shuffle the options when the Question is created
    }

    public int getImageResId() {
        return imageResId;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}


