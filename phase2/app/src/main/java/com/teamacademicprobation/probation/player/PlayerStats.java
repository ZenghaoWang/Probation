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

    // A list of all the player's completed games.
    private final ArrayList<PlayerGameStats> completedGames;
  private PlayerGameStats currGame;
  // A map of each type of game along with the player's best scores within that game.
  private Map<String, PlayerGameStats> bestGame = new HashMap<>();
  private Map<String, PlayerGameStats> avgGame = new HashMap<>();

    /**
     * Instantiates a PlayerStats object with an empty list of completed games.
     */
  PlayerStats() {
    currGame = new PlayerGameStats("");
    bestGame.put("", currGame);
    avgGame.put("", currGame);
    completedGames = new ArrayList<>();
  }

  // ==== GETTER METHODS for Player when retrieving data to be saved into the JSON

    /** @return ths player's current gameID */
  public String getCurrGameID() {
    return currGame.getGameID();
  }

    /** @return a map of all the statistics of the current game along with their values */
  public Map<String, Map<String, Integer>> getCurrStats() {
    Map<String, Map<String, Integer>> currStats = new HashMap<>();
    currStats.put(currGame.getGameID(), currGame.getAllStats());
    return currStats;
  }

    /** @return a map of all the statistics of a particular game */
  public Map<String, Integer> getBestStats(String gameID) {
    return bestGame.get(gameID).getAllStats();
  }

  /**
   * Returns a map of the player's highest scores for each game.
   *
   * @return a map of all the statistics of a game along with their associated values.
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

  // ==== SETTER METHODS

    public void setBestStats(Map<String, Map<String, Integer>> bestGameStats) {
        for (String gameKey : bestGameStats.keySet()) {
            PlayerGameStats gameStats = new PlayerGameStats(gameKey);
            gameStats.update(bestGameStats.get(gameKey));
            this.bestGame.put(gameKey, gameStats);
        }
    }

    public void setCurrStats(String currGameID, Map<String, Integer> currGameStats) {
        this.newCurrGame(currGameID);
        this.updateCurrGame(currGameStats);
    }

  // ==== UPDATE METHODS

  /**
   * Instantiates a new PlayerGameStats object with the given gameID (ie. when you start a new game)
   *
   * @param gameID which game the player is currently on.
   */
  public void newCurrGame(String gameID) {
    currGame = new PlayerGameStats(gameID);
  }

    /** Occurs at the end of the current game session. */
  public void endCurrGame(boolean save) {
    if (save) {
      completedGames.add(currGame); // adds this game to the list of this player's completed games

      updateBestGame(currGame); // updates the player's best game statistics with this game in mind
    }

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
}
