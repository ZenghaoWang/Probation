package com.teamacademicprobation.probation.player;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A builder class that uses takes in JSONObjects and creates a player with the data in the
 * JSONObject.
 *
 * <p>The player data is in the following format:
 *
 * <p>{"Username": String, "Password": String, "Preference1": String, "Game1": {Stat1: int},
 * "PlayerStat1" : int}
 */
class PlayerBuilder {

  /** The player this player builder is building. */
  private Player player;

  private static final String TAG = "PlayerBuilder";

  /** Initializes the player builder with a default player. */
  public PlayerBuilder() {
    this.player = new Player();
  }

  /**
   * Updates the player stats according to the player data.
   *
   * @param playerData The data of this player.
   */
  public void buildPlayerStats(JSONObject playerData) {
    String[] gameIDs = {"Game1", "Game2", "Game3"}; // TODO: Change to Game.getID() once implemented
    try {
      for (String gameID : gameIDs) {
        JSONObject gameStatistics = playerData.getJSONObject(gameID);
        Map<String, Integer> gameStatsMap = buildGameStatMap(gameStatistics);
        this.player.updateStats(gameID, gameStatsMap);
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player statistics");
    }
  }

  /**
   * Builds a map where the key values are the names of each statistic in PlayerGameStats and the values
   * are the corresponding integer.
   *
   * @param gameStatistics The statistics of this player. It is in the format: {"Stat1": int,
   *     "Stat2": int}
   * @return Map<String, Integer> </String,> specified above.
   */
  private Map<String, Integer> buildGameStatMap(JSONObject gameStatistics) {
    Map<String, Integer> gameStatsMap = new HashMap<>();
    String[] statIDs = {"time", "score", "items"};
    try {
      for (String statID : statIDs) {
        gameStatsMap.put(statID, gameStatistics.getInt(statID));
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting game statistics");
    }
    return gameStatsMap;
  }

  /**
   * Updates the player preferences according to the player data.
   *
   * @param playerData The data of this player.
   */
  public void buildPlayerPreferences(JSONObject playerData) {
    Map<String, String> playerPreferences = new HashMap<>();
    String[] preferenceKeys = {"Difficulty", "Color", "Avatar"};
    try {
      for (String preferenceKey : preferenceKeys) {
        playerPreferences.put(preferenceKey, playerData.getString(preferenceKey));
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player preferences.");
    }

    this.player.updatePreferences(playerPreferences);
  }

  /**
   * Updates the player's username and password.
   *
   * @param playerData The data of this player.
   */
  public void buildUserAndPassword(JSONObject playerData) {

    try {
      this.player.setUsername(playerData.getString("Username"));
      this.player.setPassword(playerData.getString("Password"));
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player data.");
    }
  }

  public void buildUserAndPassword(String username, String password){
    this.player.setUsername(username);
    this.player.setPassword(password);
  }

  /**
   * Returns the player this player builder is building.
   *
   * @return Player
   */
  public Player getPlayer() {
    return player;
  }
}
