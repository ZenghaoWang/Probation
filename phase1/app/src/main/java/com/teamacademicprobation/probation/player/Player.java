package com.teamacademicprobation.probation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

  // ==== END OF SETTER/GETTER METHODS ====

  private String generateRandomID() {
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String uppercase = lowercase.toUpperCase();
    String data = uppercase + lowercase;
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < playerIDlen; i++){
      result.append(data.charAt(ThreadLocalRandom.current().nextInt(data.length())));

    }
    return result.toString();
  }


  // == TODO: Complete these methods!
  public Map<String, Object> getData(){
    Map<String, Object> result = new HashMap<>();

    // Add username and password
    result.put("Username", this.username);
    result.put("Password", this.password);
    for(String preferenceKey : this.playerPreferences.getPreferences().keySet()){
      result.put(preferenceKey, this.playerPreferences.getPreference(preferenceKey));
    }

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
    for(String preferenceKey : playerPreferences.keySet()){
      this.playerPreferences.updatePreference(preferenceKey, playerPreferences.get(preferenceKey));
    }
  }

  /**
   * Updates the PlayerStats.
   *
   * @param gameID The gameID to update.
   * @param gameStatsMap A map with the following format: {"Stat1": int}
   */
  public void updateStats(String gameID, Map<String, Integer> gameStatsMap) {}
}
