package com.teamacademicprobation.probation.player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The statistics of a single game, keeping its GameID, date/time of initiation, and statistics.
 * Each instance of a game has its own instance of PlayerGameStats.
 */
public class PlayerGameStats implements Comparable<PlayerGameStats> {

  // The time and date of the game's initiation.
  private final Date startTime;
  private final String gameID;
  // A map of all recorded stats for one instance of a game.
  private final Map<String, Integer> statsMap = new HashMap<>();
    // The total score in this game (the value of all stats added together)
    private int totalScore;

  /** Instantiates a PlayerGameStats object with statistics time, score, and items set to 0. */
  PlayerGameStats(String id) {
    gameID = id;
    startTime = new Date();
    totalScore = 0;
  }

  // ==== GETTER METHODS

  /** @return the date and time this session of the game began. */
  public Date getStartTime() {
    return startTime;
  }

  /** @return this game's ID */
  String getGameID() {
    return gameID;
  }

  /** @return a map of each statistics and their associated scores for this game. */
  Map<String, Integer> getAllStats() {
    return statsMap;
  }

  /** @return this game's total score. */
  private int getTotalScore() {
    return totalScore;
  }

  // ==== UPDATE METHODS

  /**
   * Sets the given statistic to replace its current score with the given one.
   *
   * @param statID the statistic to be replaced.
   * @param newValue the new value for the statistic.
   */
  void update(String statID, int newValue) {
    if (statsMap.containsKey(statID)) {
      int currValue = Objects.requireNonNull(statsMap.get(statID));
      updateTotalScore(
              currValue * (-1)); // subtracts the statistic's previous value from totalScore
    }

    statsMap.put(statID, newValue);
    updateTotalScore(newValue); // adds the statistic's new value to totalScore
  }

  void update(Map<String, Integer> newStats) {
      for (String statID : newStats.keySet()) {
      statsMap.put(statID, newStats.get(statID));
      updateTotalScore(newStats.get(statID));
    }
  }

  /**
   * Updates this game's total score by a given amount.
   *
   * @param score the amount to increment totalScore by.
   */
  private void updateTotalScore(int score) {
    totalScore += score;
  }

  // ==== COMPARABLE METHOD

  /**
   * Compares the total score of this game with another's
   *
   * @param otherGameStats the other game whose statistics we're comparing to this one
   * @return this game's total score - pl's total score
   */
  public int compareTo(PlayerGameStats otherGameStats) {
    if (totalScore == otherGameStats.getTotalScore()) {
      return 0;
    } else {
      return totalScore - otherGameStats.getTotalScore();
    }
  }
}
