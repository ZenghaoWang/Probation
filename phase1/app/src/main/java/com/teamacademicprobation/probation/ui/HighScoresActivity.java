package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;

public class HighScoresActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

    }

    public void returnToHomeScreen(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
