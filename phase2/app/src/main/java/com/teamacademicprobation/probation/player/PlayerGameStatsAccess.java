package com.teamacademicprobation.probation.player;

import java.util.Map;

/**
 * An interface that defines the methods that games have access to,
 */
public interface PlayerGameStatsAccess {

    /**
     * Updates the current stats for a given game.
     *
     * @param playerID The player to save to.
     * @param gameID   The game to save the stat to.
     * @param statID   The ID of the stat being saved.
     * @param stat     The stat to be saved.
     */
    void updateStats(String playerID, String gameID, String statID, int stat);

    /**
     * Updates all the current stats for a given game.
     *
     * @param playerID The player to save to.
     * @param gameID   The game to save the stat to.
     * @param stats    A map where the keys are the statIDs and the values are the stat.
     */
    void updateStats(String playerID, String gameID, Map<String, Integer> stats);

    /**
     * Ends the current game.
     *
     * @param playerID The player to end the game for.
     * @param gameID   The game to end.
     * @param save     True if the stats are to saved for best game.
     */
    void endGame(String playerID, String gameID, boolean save);

    /**
     * Returns a map representing the current stats for a given game
     *
     * @param playerID The player to get stats from.
     * @param gameID   The game to get stats from.
     * @return A map where the keys are the statIDs and the values are the stats.
     */
    Map<String, Integer> getCurrStats(String playerID, String gameID);

    /**
     * A boolean that represents the whether or not the game is currently played.
     *
     * @param playerID The player to check.
     * @param gameID   The game to check.
     * @return True if the the game is being played.
     */
    boolean isBeingPlayed(String playerID, String gameID);
}
