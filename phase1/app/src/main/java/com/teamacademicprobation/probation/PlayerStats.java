package com.teamacademicprobation.probation;

import java.util.Map;
import java.util.HashMap;

/**
 * A player's game statistics across all their played games.
 * Keeps a record of each of this player's GameStats.
 * Each player has an instance of PlayerStats.
 */
public class PlayerStats {

    // A map of all the player's games previously played, along with each game's stats.
    private final Map<String, GameStats> gameStatsMap = new HashMap<>();

    // A GameStats object to contain the player's highest score in every stat across all games.
    private final GameStats bestGame = new GameStats();

    /**
     * Instantiates a PlayerStats object.
     */
    PlayerStats() {
    }

    /**
     * Adds a game's stats to the player's history of games played
     *
     * @param GameID the ID associated with the game.
     * @param Stats  the game's statistics.
     */
    public void addGameStats(String GameID, GameStats Stats) {
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
    public GameStats getGameStats(String GameID) {
        return gameStatsMap.get(GameID);
    }

    /**
     * Returns the player's best statistics among all their played games.
     */
    public GameStats getBestScore() {
        return bestGame;
    }

}
