package com.teamacademicprobation.probation.ui.highscores;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerTotalStatsAccess;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HighScoresActivity extends AppCompatActivity {
    String playerID;
    HighScoresModel model;

  /**
   * Generate strings and display them on the ListView.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_high_scores);

    playerID = getIntent().getStringExtra(LoginActivity.PLAYER_ID_KEY);
    model = new HighScoresModel(playerID);

    ListView scoreList = findViewById(R.id.statsListView);
    ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, model.getScoreStrings());
    scoreList.setAdapter(adapter);
  }


  /**
   * Returns to the home screen / main activity.
   *
   * @param view The button.
   */
  public void returnToHomeScreen(View view) {
    finish();
  }
}
