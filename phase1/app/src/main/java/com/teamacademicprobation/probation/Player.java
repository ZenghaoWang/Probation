package com.teamacademicprobation.probation;

/** A player for the game. */
public class Player {

  /** The statistics of this player. */
  private PlayerStats playerStats;

  /** The preferences of this player. */

  // TODO: Can we just use a map for playerPreferences? Hmm..
  private PlayerPreferences playerPreferences;

  /** The username of this player.*/
  private String username;

  /** The password of this player. */
  private String password;

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

    public void updateScore(int newScore, String gameID){
      playerStats.updateScore(newScore, gameID);
    }

}
