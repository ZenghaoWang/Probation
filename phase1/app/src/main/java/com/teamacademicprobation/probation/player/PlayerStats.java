package com.teamacademicprobation.probation.player;

import java.util.Map;
import java.util.HashMap;

/**
 * A player's game statistics across all their played games. Keeps a record of each of this player's
 * PlayerGameStats. Each player has an instance of PlayerStats.
 */
public class PlayerStats {

  // A map of all the player's games previously played, along with each game's stats.
  private final Map<String, PlayerGameStats> gameStatsMap = new HashMap<>();

  // A PlayerGameStats object to contain the player's highest score in every stat across all games.
  private final PlayerGameStats bestGame = new PlayerGameStats();

  /** Instantiates a PlayerStats object. */
  public PlayerStats() {}

  /**
   * Adds a game's stats to the player's history of games played
   *
   * @param GameID the ID associated with the game.
   * @param Stats the game's statistics.
   */
  public void addGameStats(String GameID, PlayerGameStats Stats) {
    gameStatsMap.put(GameID, Stats);

    if (Stats.getStat("time") > bestGame.getStat("time")) {
      bestGame.setStat("time", Stats.getStat("time"));
    }
    if (Stats.getStat("score") > bestGame.getStat("score")) {
      bestGame.setStat("score", Stats.getStat("score"));
    }
    if (Stats.getStat("items") > bestGame.getStat("items")) {
      bestGame.setStat("items", Stats.getStat("items"));
    }
  }

  /**
   * Returns a specific game's stats given its ID.
   *
   * @param GameID the ID of the game.
   * @return the statistics recorded for that game.
   */
  public PlayerGameStats getGameStats(String GameID) {
    return gameStatsMap.get(GameID);
  }

  /** Returns the player's best statistics among all their played games. */
  public PlayerGameStats getBestScore() {
    return bestGame;
  }
}
