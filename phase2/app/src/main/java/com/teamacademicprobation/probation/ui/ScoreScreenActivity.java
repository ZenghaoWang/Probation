package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;

/**
 * The screen that appears when a player has completed a game. A shared activity used by all game
 * implementations.
 */
public class ScoreScreenActivity extends AppCompatActivity {

    public static final String SCORE_KEY = "score";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score_screen);

    displayScoreMessage();
  }

    /**
     * Capture the message to be displayed from the intent and display it.
     */
    private void displayScoreMessage() {
        TextView scoreMessage = findViewById(R.id.scoreMessage);
        Intent intent = getIntent();
        scoreMessage.setText(intent.getStringExtra(SCORE_KEY));
    }

    /**
     * Return to the home-screen.
     * @param view The return to main menu button.
     */
    public void returnToHomeScreen(View view) {
        finish();
    }
}
