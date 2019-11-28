package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.Player;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerTotalStatsAccess;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TODO: IMPLEMENT MVP FOR THIS.
public class HighScoresActivity extends AppCompatActivity {

  private Player player;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_high_scores);

    // TODO: FIX THIS.
    String[] arr = getScoreStrings();

    ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
    TextView label = findViewById(R.id.txtPlayerStatsTitle);
    ListView list = findViewById(R.id.statsListView);
    list.setAdapter(adapter);
  }

  /**
   * Gets the Strings that represent the scores of each game.
   *
   * @return A String Array that contains the string representations of each game.
   */
  private String[] getScoreStrings() {

    String[] result;
    String playerID = getIntent().getStringExtra(LoginActivity.PLAYER_ID_KEY);
    PlayerTotalStatsAccess playerTotalStatsAccess = new PlayerManager();
    List<String> statsToShow = new ArrayList<>();
    List<String> gameIDs = playerTotalStatsAccess.getGamesPlayed(playerID);
    for (String gameID : gameIDs) {
      buildStrings(playerID, playerTotalStatsAccess, statsToShow, gameID);
    }
    result = new String[statsToShow.size()];
    statsToShow.toArray(result);
    return result;
  }

  /**
   * A helper method that builds the string that represent each game and it's statistics, and
   * appends it to the list statsToShow.
   *
   * @param playerID The playerID of this game.
   * @param playerTotalStatsAccess An object that allows some access into the player.
   * @param statsToShow The list to append the results onto.
   * @param gameID The gameID of the game to retrieve statistics from.
   */
  private void buildStrings(
          String playerID, PlayerTotalStatsAccess playerTotalStatsAccess, List<String> statsToShow, String gameID) {
    StringBuilder toShow = new StringBuilder(gameID + ": ");
    Map<String, Integer> gameStatMap = playerTotalStatsAccess.getBestStats(playerID, gameID);
    for (String statID : gameStatMap.keySet()) {
      toShow.append(statID).append(": ").append(gameStatMap.get(statID));
    }
    statsToShow.add(toShow.toString());
  }

  /**
   * Returns to the home screen / main activity.
   *
   * @param view The button.
   */
  public void returnToHomeScreen(View view) {
    startActivity(new Intent(this, MainActivity.class));
  }
}
