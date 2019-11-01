package com.teamacademicprobation.probation.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A player's game statistics across all their played games. Keeps a record of this player's current
 * game, current game's stats, their best game stats, and all the games they've played.
 */
public class PlayerStats {

  private PlayerGameStats currGame;

  // A map of each type of game along with the player's best scores within that game.
  private Map<String, PlayerGameStats> bestGame = new HashMap<>();

  // A list of all the player's completed games.
  private final ArrayList<PlayerGameStats> completedGames; // how to retrieve specific game stats?

  /** Instantiates a PlayerStats object with an empty list of completed games. */
  PlayerStats() {
    currGame = new PlayerGameStats("");
    bestGame.put("", currGame);
    completedGames = new ArrayList<>();
  }

  /**
   * Instantiates a new PlayerGameStats object with the given gameID (ie. when you start a new game)
   *
   * @param gameID which game the player is currently on.
   */
  public void newGame(String gameID) {
    currGame = new PlayerGameStats(gameID);
  }

  /** Occurs at the end of the current game session. */
  public void endCurrGame() {
    completedGames.add(currGame); // adds this game to the list of this player's completed games

    updateBestGame(currGame); // updates the player's best game statistics with this game in mind

    currGame
        .setComplete(); // sets the current game to complete (meaning it's not the current level)
    currGame =
        new PlayerGameStats(
            ""); // sets the current game to null (meaning there is currently no level being played)
  }

  /**
   * Updates a single statistic within the current game.
   *
   * @param statID the statistic to be updated.
   * @param value the value we want associated with that statistic.
   */
  public void updateCurrGame(String statID, int value) {
    currGame.update(statID, value);
  }

  /**
   * Updates a map of statistics within the current game.
   *
   * @param newStats a map of statistics plus the values we want associated with them
   */
  public void updateCurrGame(Map<String, Integer> newStats) {
    newStats.forEach((k, v) -> currGame.update(k, v));
  }

  /**
   * Updates the statistics of the player's best game if the new statistics are better
   *
   * @param otherGameStats the statistics of the game to be compared with the best game statistics
   */
  private void updateBestGame(PlayerGameStats otherGameStats) {
    String gameID = otherGameStats.getGameID();

    if (bestGame.containsKey(gameID)) {
      if (Objects.requireNonNull(bestGame.get(gameID)).compareTo(otherGameStats) < 0) {
        bestGame.put(gameID, otherGameStats);
      }
    } else {
      bestGame.put(gameID, otherGameStats);
    }
  }

  public String getCurrLevel() {
    return currGame.getGameID();
  }

  /**
   * Returns a map of all the statistics of the current game along with their values.
   *
   * @return the map
   */
  public Map<String, Integer> getCurr() {
    return currGame.getAllStats();
  }

  /**
   * Returns the current game's current score for a certain statistic.
   *
   * @param statID the statistic to be returned.
   * @return the statistic's value.
   */
  public int getCurr(String statID) {
    return currGame.getStat(statID);
  }

  /**
   * Returns a map of the player's highest scores for each game.
   *
   * @return a map of all the statistics of a game along with their associated values.
   */
  public Map<String, PlayerGameStats> getBest() {
    return Objects.requireNonNull(bestGame);
  }

  /**
   * Returns a map of the player's highest scores for each game.
   *
   * @return a map of all the statistics of a game along with their associated values.
   */
  public Map<String, Integer> getBest(String gameID) {
    return Objects.requireNonNull(bestGame.get(gameID)).getAllStats();
  }

  /**
   * Returns the "highest score" for a given game type's given statistic.
   *
   * @param gameID the game whose statistics we want.
   * @param statID the statistic we want.
   * @return a map of the game's statistics.
   */
  public int getBest(String gameID, String statID) {
    return Objects.requireNonNull(bestGame.get(gameID)).getStat(statID);
  }
}
