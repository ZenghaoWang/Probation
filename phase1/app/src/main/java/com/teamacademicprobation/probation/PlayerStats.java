package com.teamacademicprobation.probation;

import java.util.HashMap;
import java.util.Map;

/**
 * The statistics of a player, such as total score, best score, etc.
 * Each game has its own statistic.
 */
public class PlayerStats {
    Map<String, GameStats> gameStatsMap =  new HashMap<>();

    public void updateScore(int newScore, String gameID) {
    }
}
