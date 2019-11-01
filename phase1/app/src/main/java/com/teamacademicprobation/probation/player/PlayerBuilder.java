package com.teamacademicprobation.probation.player;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A builder class that uses takes in JSONObjects and creates a player with the data in the
 * JSONObject.
 *
 * <p>The player data is in the following format:
 *
 * <p>{"Username": "playerUsername, "Password": "playerPassword", "Preferences" : {"PreferenceKey" :
 * "thisPreference"}, "CurrentSession" : {"GameID" : { "StatID" : "statistic"}}, "BestSession":
 * {"GameID": {"StatID" : "statistic"}}
 */
public class PlayerBuilder {

  /** The player this player builder is building. */
  private Player player;

  private static final String TAG = "PlayerBuilder";

  /** Initializes the player builder with a default player. */
  public PlayerBuilder() {
    this.player = new Player();
  }

  public PlayerBuilder(String username, String password) {
    this.player = new Player(username, password);
  }

  /**
   * Updates the player stats according to the player data.
   *
   * @param playerData The data of this player.
   */
  //  public void buildPlayerStats(JSONObject playerData) {
  //    String[] gameIDs = {"Game1", "Game2", "Game3"}; // TODO: Change to Game.getID() once
  // implemented
  //    try {
  //      for (String gameID : gameIDs) {
  //        JSONObject gameStatistics = playerData.getJSONObject(gameID);
  //        Map<String, Integer> gameStatsMap = buildGameStatMap(gameStatistics);
  //        this.player.updateStats(gameID, gameStatsMap);
  //      }
  //    } catch (JSONException e) {
  //      Log.e(TAG, "Error in getting player statistics");
  //    }
  //  }

  public void buildPlayer(JSONObject playerData, String playerID) {
    buildPlayerID(playerID);
    buildPlayerStats(playerData);
    buildPlayerPreferences(playerData);
    buildUserAndPassword(playerData);
  }

  private void buildPlayerStats(JSONObject playerData) {
    try {

      JSONObject currGameStats = playerData.getJSONObject("CurrentSession");
      JSONObject bestGameStats = playerData.getJSONObject("BestSession");

      buildCurrGameStats(currGameStats);
      buildBestGameStats(bestGameStats);
    } catch(JSONException e) {
      Log.e(TAG, e.toString());
    }
  }

  private void buildBestGameStats(JSONObject bestGameStats) {
    Iterator<String> gameIDs = bestGameStats.keys();
    while (gameIDs.hasNext()) {
      String currGameID = gameIDs.next();
      Map<String, Integer> currGameStatsMap = null;
      try {
        currGameStatsMap = buildGameStatMap(bestGameStats.getJSONObject(currGameID));
      } catch (JSONException e) {
        Log.e(TAG, e.toString());
      }
      //TODO: Implement this. 
//      this.player.loadBestSession(currGameID, currGameStatsMap);
    }
  }

  private void buildCurrGameStats(JSONObject currGameStats) {
    Iterator<String> gameIDs = currGameStats.keys();
    while (gameIDs.hasNext()) {
      String currGameID = gameIDs.next();
      Map<String, Integer> currGameStatsMap = null;
      try {
        currGameStatsMap = buildGameStatMap(currGameStats.getJSONObject(currGameID));
      } catch (JSONException e) {
        Log.e(TAG, e.toString());
      }
      this.player.updateCurrGame(currGameID);
      this.player.updateCurrStats(currGameStatsMap);
    }
  }

  /**
   * Builds a map where the key values are the names of each statistic in PlayerGameStats and the
   * values are the corresponding integer.
   *
   * @param gameStatistics The statistics of this player. It is in the format: {"StatID1": "S,
   *     "Stat2": int}
   * @return Map<String, Integer> </String,> specified above.
   */
  private Map<String, Integer> buildGameStatMap(JSONObject gameStatistics) {
    Map<String, Integer> gameStatsMap = new HashMap<>();
    String[] statIDs = {"time", "score", "items"}; // Player.getStatIDs();
    try {
      for (String statID : statIDs) {
        gameStatsMap.put(statID, Integer.valueOf(gameStatistics.getString(statID)));
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
  private void buildPlayerPreferences(JSONObject playerData) {
    Map<String, String> playerPreferencesMap = new HashMap<>();
    String[] preferenceKeys = {"Difficulty", "Color", "Avatar"}; // Player.getPreferenceKeys();
    try {
      JSONObject playerPreferences = playerData.getJSONObject("Preferences");
      for (String preferenceKey : preferenceKeys) {
        playerPreferences.put(preferenceKey, playerPreferences.getString(preferenceKey));
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player preferences.");
    }

    this.player.updatePreferences(playerPreferencesMap);
  }

  /**
   * Updates the player's username and password.
   *
   * @param playerData The data of this player.
   */
  private void buildUserAndPassword(JSONObject playerData) {

    try {
      this.player.setUsername(playerData.getString("Username"));
      this.player.setPassword(playerData.getString("Password"));
    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player data.");
    }
  }

  /**
   * Returns the player this player builder is building.
   *
   * @return Player
   */
  public Player getPlayer() {
    return player;
  }

  private void buildPlayerID(String playerID) {
    this.player.setPlayerID(playerID);
  }
}
