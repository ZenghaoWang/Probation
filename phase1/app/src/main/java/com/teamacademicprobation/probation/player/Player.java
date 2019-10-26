package com.teamacademicprobation.probation.player;

import java.util.HashMap;
import java.util.Map;

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

  public Player() {
    this("defaultUser", "defaultPass");
  }

  public Player(String username, String password) {
    this.username = username;
    this.password = password;
    this.playerStats = new PlayerStats();
    this.playerPreferences = new PlayerPreferences();
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

  // == TODO: Complete these methods!
  public Map<String, Object> getData() {
    return new HashMap<>();
  }

  /**
   * Returns the playerID of this player.
   *
   * @return PlayerID.
   */
  public String getPlayerID() {
    return "";
  }

  /**
   * Updates PlayerPreferences.
   *
   * @param playerPreferences A map with the following format: {"Preference1": String}
   */
  public void updatePreferences(Map<String, String> playerPreferences) {}

  /**
   * Updates the PlayerStats.
   *
   * @param gameID The gameID to update.
   * @param gameStatsMap A map with the following format: {"Stat1": int}
   */
  public void updateStats(String gameID, Map<String, Integer> gameStatsMap) {}
}
