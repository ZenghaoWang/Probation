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
 * <p>{"Username": "playerUsername, "Password": "playerPassword", "PreferencesActivity" :
 * {"PreferenceKey" : "thisPreference"}, "CurrentSession" : {"GameID" : { "StatID" : "statistic"}},
 * "BestSession": {"GameID": {"StatID" : "statistic"}}
 */
public class PlayerBuilder {

  private static final String TAG = "PlayerBuilder";
    /**
     * The player this player builder is building.
     */
    private Player player;

    /**
     * Initializes the player builder with a default player.
     */
  public PlayerBuilder() {
    this.player = new Player();
  }

  public PlayerBuilder(String username, String password) {
    this.player = new Player(username, password);
  }

  /**
   * Starts building this player given it's playerData and playerID.
   *
   * @param playerData The JSONObject that represents the data.
   * @param playerID The playerID of this player.
   */
  public void buildPlayer(JSONObject playerData, String playerID) {
    buildPlayerID(playerID);
    buildPlayerStats(playerData);
    buildPlayerPreferences(playerData);
    buildUserAndPassword(playerData);
  }

  /**
   * Builds the player stats.
   *
   * @param playerData THe JSONObject that represents the data.
   */
  private void buildPlayerStats(JSONObject playerData) {
    try {
      JSONObject bestGameStats = playerData.getJSONObject("Best Session");
      buildBestGameStats(bestGameStats);
    } catch (JSONException e) {
      JSONObject bestGameStats = new JSONObject();
      buildBestGameStats(bestGameStats);
    }

    try {
      JSONObject currGameStats = playerData.getJSONObject("Current Session");
      buildCurrGameStats(currGameStats);
    } catch (JSONException e) {
      JSONObject currGameStats = new JSONObject();
      buildCurrGameStats(currGameStats);
    }
  }

  /**
   * Builds the best session stats.
   *
   * @param bestGameStats The JSONObject that represents the best session.
   */
  private void buildBestGameStats(JSONObject bestGameStats) {
    Iterator<String> gameIDs = bestGameStats.keys();
    Map<String, Map<String, Integer>> allBestGameStats = new HashMap<>();

    while (gameIDs.hasNext()) {
      String bestGameID = gameIDs.next();
      Map<String, Integer> bestGameStatsMap = null;
      try {
        bestGameStatsMap = buildGameStatMap(bestGameStats.getJSONObject(bestGameID));
      } catch (JSONException e) {
        Log.e(TAG, e.toString());
      }
      allBestGameStats.put(bestGameID, bestGameStatsMap);
    }
    player.setBestStats(allBestGameStats);
  }

  /**
   * Builds the current session stats.
   *
   * @param currGameStats The JSONObject that represents the current session.
   */
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

      this.player.setCurrStats(currGameID, currGameStatsMap);
    }
  }

  /**
   * Builds a map where the key values are the names of each statistic in GameStatsMap and the
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

      while (statIDs.hasNext()) {
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
      JSONObject playerPreferences = playerData.getJSONObject("PreferencesActivity");
      Iterator<String> preferenceKeys = playerPreferences.keys();
      while (preferenceKeys.hasNext()) {
        String preferenceKey = preferenceKeys.next();
        playerPreferences.put(preferenceKey, playerPreferences.getString(preferenceKey));
      }

    } catch (JSONException e) {
      Log.e(TAG, "Error in getting player preferences.");
    }

    this.player.setPreferences(playerPreferencesMap);
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
