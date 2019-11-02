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


  public void buildPlayer(JSONObject playerData, String playerID) {
    buildPlayerID(playerID);
    buildPlayerStats(playerData);
    buildPlayerPreferences(playerData);
    buildUserAndPassword(playerData);
  }

  private void buildPlayerStats(JSONObject playerData) {
    try {
      JSONObject bestGameStats = playerData.getJSONObject("Best Session");
      buildBestGameStats(bestGameStats);
    } catch(JSONException e) {
      JSONObject bestGameStats = new JSONObject();
      buildBestGameStats(bestGameStats);
    }

    try {
      JSONObject currGameStats = playerData.getJSONObject("Current Session");
      buildCurrGameStats(currGameStats);
    } catch(JSONException e) {
      JSONObject currGameStats = new JSONObject();
      buildCurrGameStats(currGameStats);
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
      player.newCurrGame(currGameID);
      player.updateCurrStats(currGameStatsMap);
      player.endCurrGame();
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
      this.player.newCurrGame(currGameID);
      this.player.updateCurrStats(currGameStatsMap);
      this.player.endCurrGame();
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
//    String[] statIDs = {"score"}; // TODO : Player.getStatIDs();
    Iterator<String> statIDs = gameStatistics.keys();
    try {

      while(statIDs.hasNext()){
        String statID = statIDs.next();
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
  private void buildPlayerPreferences(JSONObject playerData) {
    Map<String, String> playerPreferencesMap = new HashMap<>();
    try {
      JSONObject playerPreferences = playerData.getJSONObject("Preferences");
      Iterator<String> preferenceKeys = playerPreferences.keys();
      while(preferenceKeys.hasNext()){
        String preferenceKey = preferenceKeys.next();
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
