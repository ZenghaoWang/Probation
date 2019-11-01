package com.teamacademicprobation.probation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** A player for the game. */
public class Player {

  /** The statistics of this player. */
  private PlayerStats playerStats;

  /** The preferences of this player. */

  // TODO: Can we just use a map for playerPreferences? Hmm..
  private PlayerPreferences playerPreferences;

  /** The username of this player. */
  private String username;

  /** The password of this player. */
  private String password;

  /** The random playerID of this player */
  private String playerID;

  private static final int playerIDlen = 5;

  public Player() {
    this("defaultUser", "defaultPass");
  }

  public Player(String username, String password) {
    this.username = username;
    this.password = password;
    this.playerStats = new PlayerStats();
    this.playerPreferences = new PlayerPreferences();
    this.playerID = generateRandomID();
  }

  // ==== SETTER/GETTER METHODS ====
  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPlayerID(String playerID) {
    this.playerID = playerID;
  }

  // ==== END OF SETTER/GETTER METHODS ====

  private String generateRandomID() {
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String uppercase = lowercase.toUpperCase();
    String data = uppercase + lowercase;
    Random random = new Random();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < playerIDlen; i++) {
      result.append(data.charAt(random.nextInt(data.length())));
    }
    return result.toString();
  }

  public Map<String, Object> getData() {
    Map<String, Object> result = new HashMap<>();

    // Add username and password
    result.put("Username", this.username);
    result.put("Password", this.password);

    Map<String, String> preferences = new HashMap<>();
    for (String preferenceKey : this.playerPreferences.getPreferences().keySet()) {
      preferences.put(preferenceKey, this.playerPreferences.getPreference(preferenceKey));
    }
    result.put("Preferences", preferences);

    Map<String, Map<String, Integer>> currStats = new HashMap<>();
    currStats.put(
        this.playerStats.getCurrGameID(), this.playerStats.getCurr()); // this is null so fix it
    result.put("Current Session", currStats);

    Map<String, Map<String, Integer>> bestStats = new HashMap<>();
    for (String gameKey : this.playerStats.getBest().keySet()) {
      if (!gameKey.equals("")) {
        bestStats.put(gameKey, this.playerStats.getBest(gameKey));
      }
    }
    result.put("Best Session", bestStats);

    return result;
  }

  /**
   * Returns the playerID of this player.
   *
   * @return PlayerID.
   */
  public String getPlayerID() {
    return this.playerID;
  }

  /**
   * Updates PlayerPreferences.
   *
   * @param playerPreferences A map with the following format: {"Preference1": String}
   */
  public void updatePreferences(Map<String, String> playerPreferences) {
    for (String preferenceKey : playerPreferences.keySet()) {
      this.playerPreferences.updatePreference(preferenceKey, playerPreferences.get(preferenceKey));
    }
  }

  /**
   * Lets you update the player's current game session with a new game.
   *
   * @param currGameID the new game's ID
   */
  public void newCurrGame(String currGameID) {
    this.playerStats.newGame(currGameID);
  }

  /** Lets you update the player's current game session with a new game. */
  public void endCurrGame() {
    this.playerStats.endCurrGame();
  }

  /**
   * Updates the PlayerStats for the current game.
   *
   * @param statID the statistic to be updated
   * @param value the new value of the statistic
   */
  public void updateCurrStats(String statID, int value) {
    this.playerStats.updateCurrGame(statID, value);
  }

  /**
   * Updates the PlayerStats for the current game.
   *
   * @param gameStatsMap A map with the following format: {"Stat1": int}
   */
  public void updateCurrStats(Map<String, Integer> gameStatsMap) {
    this.playerStats.updateCurrGame(gameStatsMap);
  }

  public String getCurrGameID() {
    return this.playerStats.getCurrGameID();
  }

  public Map<String, Integer> getBest(String gameID){
    return this.playerStats.getBest(gameID);
  }
}
