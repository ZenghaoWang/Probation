package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.PlayerGameStats;
import com.teamacademicprobation.probation.player.PlayerGameStatsAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

/**
 * The screen that appears when a player has completed a game. A shared activity used by all game
 * implementations.
 */
public class ScoreScreenActivity extends AppCompatActivity {

    public static final String SCORE_KEY = "SCORE";
    public static final String GAMEID_KEY = "GAMEID";
    public static final String PLAYERID_KEY = "PLAYERID";

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
     *
     * @param view The return to main menu button.
     */
    public void returnToHomeScreen(View view) {
        CheckBox checkBox = findViewById(R.id.checkBox);
        String playerID = getIntent().getStringExtra(PLAYERID_KEY);
        String gameID = getIntent().getStringExtra(GAMEID_KEY);
        PlayerGameStatsAccess playerAccess = new PlayerManager();
        playerAccess.endGame(playerID, gameID, checkBox.isChecked());

        finish();
    }
}
