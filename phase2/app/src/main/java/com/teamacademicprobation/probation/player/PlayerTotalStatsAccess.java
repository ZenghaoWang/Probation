package com.teamacademicprobation.probation.player;

import java.util.List;
import java.util.Map;

/**
 * An interface that defines the methods that the High score screen gets access to.
 */
public interface PlayerTotalStatsAccess {

    /**
     * Returns the best scores of a specific game for a specific player.
     *
     * @param playerID The player to get the best stats for.
     * @param gameID   The game to get the best stats for.
     * @return A map where the keys are the statIDs and the values are the stats.
     */
    Map<String, Integer> getBestStats(String playerID, String gameID);

    /**
     * Returns the total scores of a specific game for a specific player.
     *
     * @param playerID The player to get the total stats for.
     * @param gameID   The game to get the total stats for.
     * @return A map where the keys are the statIDs and the values are the stats.
     */
    Map<String, Integer> getTotalStats(String playerID, String gameID);

    /**
     * Returns a list of the games played.
     *
     * @param playerID The player to get games for.
     * @return A list of the gameIDs of all the games played by this player.
     */
    List<String> getGamesPlayed(String playerID);
}
