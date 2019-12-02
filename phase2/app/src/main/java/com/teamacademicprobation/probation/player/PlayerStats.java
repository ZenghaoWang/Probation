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

  private Map<String, PlayerGameStats> currGame = new HashMap<>();
  // A map of each type of game along with the player's best scores within that game.
  private Map<String, PlayerGameStats> bestGame = new HashMap<>();
  private Map<String, PlayerGameStats> totalGame = new HashMap<>();

  /** Instantiates a PlayerStats object with an empty list of completed games. */
  PlayerStats() {
    PlayerGameStats emptyGame = new PlayerGameStats("");
    currGame.put("", emptyGame);
    bestGame.put("", emptyGame);
    totalGame.put("", emptyGame);
  }

  // ==== GETTER METHODS for Player when retrieving data to be saved into the JSON

  /**
   * Returns whether a particular game is being played (whether a current session of it is stored in
   * the player data)
   *
   * @param gameID the game whose info we want to retrieve
   * @return whether any stats for an ongoing session of this game exist
   */
  public boolean isBeingPlayed(String gameID) {
    if (currGame.containsKey(gameID)) {
      return true;
    }
    return false;
  }

  /**
   * Returns a map of all the current statistics for a specific game
   *
   * @param gameID the game whose current stats we want to retrieve
   * @return a map of all the current statistics for a particular game
   */
  public Map<String, Integer> getCurrStats(String gameID) {
    return currGame.get(gameID).getAllStats();
  }

  /**
   * Returns a map of all the best statistics recorded for a specific game
   *
   * @param gameID the game whose best stats we want to retrieve
   * @return a map of all the best statistics for a particular game
   */
  public Map<String, Integer> getBestStats(String gameID) {
    return bestGame.get(gameID).getAllStats();
  }

  /**
   * Returns a map of all the total (accumulated) statistics for a single game
   *
   * @param gameID the game whose total stats we want to retrieve
   * @return a map of all the total statistics for a particular game
   */
  public Map<String, Integer> getTotalStats(String gameID) {
    return totalGame.get(gameID).getAllStats();
  }

  /**
   * Returns a map of all games currently being played along with all their current statistic values
   *
   * @return a map of all the statistics of all current games along with each game's ID
   */
  public Map<String, Map<String, Integer>> getCurrStats() {
    Map<String, Map<String, Integer>> currStats = new HashMap<>();
    for (String gameKey : this.currGame.keySet()) {
      if (!gameKey.equals("")) {
        currStats.put(gameKey, Objects.requireNonNull(this.currGame.get(gameKey).getAllStats()));
      }
    }
    return currStats;
  }

  /**
   * Returns a map of the player's highest scores for each game.
   *
   * @return a map of all the best statistics of a game along with each game's ID
   */
  public Map<String, Map<String, Integer>> getBestStats() {
    Map<String, Map<String, Integer>> bestStats = new HashMap<>();
    for (String gameKey : this.bestGame.keySet()) {
      if (!gameKey.equals("")) {
        bestStats.put(gameKey, Objects.requireNonNull(this.bestGame.get(gameKey).getAllStats()));
      }
    }
    return bestStats;
  }

  /**
   * Returns a map of the player's accumulated stats for each game they've ever played.
   *
   * @return a map of all the accumulated stats of each game along with each game's ID
   */
  public Map<String, Map<String, Integer>> getTotalStats() {
    Map<String, Map<String, Integer>> totalStats = new HashMap<>();
    for (String gameKey : this.totalGame.keySet()) {
      if (!gameKey.equals("")) {
        totalStats.put(gameKey, Objects.requireNonNull(this.totalGame.get(gameKey).getAllStats()));
      }
    }
    return totalStats;
  }

  // ==== SETTER METHODS

  /**
   * Overwrites a player's best stats for each game
   *
   * @param bestGameStats a map of game IDs alongside the best stats recorded for each game
   */
  public void setBestStats(Map<String, Map<String, Integer>> bestGameStats) {
    for (String gameKey : bestGameStats.keySet()) {
      PlayerGameStats gameStats = new PlayerGameStats(gameKey);
      gameStats.update(bestGameStats.get(gameKey));
      this.bestGame.put(gameKey, gameStats);
    }
  }

  /**
   * Overwrites a player's accumulated stats for each game
   *
   * @param totalGameStats a map of game IDs alongside the total stats recorded for each of them
   */
  public void setTotalStats(Map<String, Map<String, Integer>> totalGameStats) {
    for (String gameKey : totalGameStats.keySet()) {
      PlayerGameStats gameStats = new PlayerGameStats(gameKey);
      gameStats.update(totalGameStats.get(gameKey));
      this.totalGame.put(gameKey, gameStats);
    }
  }

  /**
   * Overwrites the games which the player is currently playing
   *
   * @param currGameStats a map of game IDs alongside the current stats for each game
   */
  public void setCurrStats(Map<String, Map<String, Integer>> currGameStats) {
    for (String gameKey : currGameStats.keySet()) {
      PlayerGameStats gameStats = new PlayerGameStats(gameKey);
      gameStats.update(currGameStats.get(gameKey));
      this.currGame.put(gameKey, gameStats);
    }
  }

  // ==== UPDATE METHODS

  /**
   * Instantiates a new PlayerGameStats object with the given gameID (ie. when you start a new game)
   *
   * @param gameID which game the player is currently on.
   */
  public void newCurrGame(String gameID) {
    currGame.put(gameID, new PlayerGameStats(gameID));
  }

  /**
   * Occurs at the end of the current game session. Removes the game from the player's list of
   * current games, and saves the game data if the player chooses to do so
   *
   * @param gameID the ID of the game we want to set as complete
   * @param save whether this completed game's data is to be recorded in the player's statistics
   */
  public void endCurrGame(String gameID, boolean save) {
    if (save) {
      updateBestGame(
          currGame.get(gameID)); // updates the player's best game statistics with this game in mind
      updateTotalGame(currGame.get(gameID));
    }

    currGame.remove(
        gameID); // sets the current game to null (meaning there is currently no level being played)
  }

  /**
   * Updates a single statistic within the current game.
   *
   * @param statID the statistic to be updated.
   * @param value the value we want associated with that statistic.
   */
  public void updateCurrGame(String gameID, String statID, int value) {
    if (!currGame.containsKey(gameID)) {
      newCurrGame(gameID);
    }
    currGame.get(gameID).update(statID, value);
  }

  /**
   * Updates a map of statistics within the current game.
   *
   * @param newStats a map of statistics plus the values we want associated with them
   */
  public void updateCurrGame(String gameID, Map<String, Integer> newStats) {
    if (!currGame.containsKey(gameID)) {
      newCurrGame(gameID);
    }
    currGame.get(gameID).update(newStats);
  }

  /**
   * Updates the statistics of the player's best game if the new statistics are better
   *
   * @param newestGameStats the statistics of the game to be compared with the best game statistics
   */
  private void updateBestGame(PlayerGameStats newestGameStats) {
    String gameID = newestGameStats.getGameID();

    if (bestGame.containsKey(gameID)) {
      if (Objects.requireNonNull(bestGame.get(gameID)).compareTo(newestGameStats) < 0) {
        bestGame.put(gameID, newestGameStats);
      }
    } else {
      bestGame.put(gameID, newestGameStats);
    }
  }

  /**
   * Updates the player's accumulated game stats to include a new set of stats
   *
   * @param newestGameStats
   */
  private void updateTotalGame(PlayerGameStats newestGameStats) {
    String gameID = newestGameStats.getGameID();
    Map<String, Integer> newStats = newestGameStats.getAllStats();
    if (totalGame.containsKey(gameID)) {
      totalGame.get(gameID).increment(newStats);
    } else {
      totalGame.put(gameID, newestGameStats);
    }
  }
}
