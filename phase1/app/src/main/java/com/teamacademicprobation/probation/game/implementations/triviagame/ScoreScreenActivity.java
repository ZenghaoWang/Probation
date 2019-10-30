package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;

/**
 * The screen that appears when a player has completed a game.
 */
public class ScoreScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        displayScoreMessage();


    }

    /**
     * Captures the message to be displayed from the intent.
     */
    private void displayScoreMessage() {
        TextView scoreMessage = findViewById(R.id.scoreMessage);
        Intent intent = getIntent();
        scoreMessage.setText(intent.getStringExtra(TriviaGameActivity.SCORE));
    }
}
