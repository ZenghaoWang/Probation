package com.teamacademicprobation.probation.player;

import java.util.Map;
import java.util.HashMap;

/**
 * The statistics of a single game, keeping time, items, and score. Each instance of a game has its
 * own instance of StatsMap.
 */
public class GameStats {

  // The status of the game (whether it is ongoing or complete).
  private boolean complete;

  // A map of all recorded stats for one instance of a game.
  private final Map<String, Integer> statsMap = new HashMap<>();

  /** Instantiates a GameStats object with statistics time, score, and items set to 0. */
  public GameStats() {
    statsMap.put("time", 0);
    statsMap.put("score", 0);
    statsMap.put("items", 0);
    complete = false;
  }

  /**
   * Returns whether this game has been completed.
   *
   * @return complete
   */
  public boolean isComplete() {
    return complete;
  }

  /** Changes this game's status from incomplete to complete. */
  public void setComplete() {
    complete = true;
  }

  /**
   * Sets the given statistic to record the given score.
   *
   * @param StatID the name of the stat to be updated.
   * @param score the score to be recorded for a specific stat.
   */
  void setStat(String StatID, int score) {
    statsMap.put(StatID, score);
  }

  /**
   * Returns the score associated with the given statistic.
   *
   * @param StatID the name of the stat whose data is to be retrieved.
   */
  int getStat(String StatID) {
    return statsMap.get(StatID);
  }
}
