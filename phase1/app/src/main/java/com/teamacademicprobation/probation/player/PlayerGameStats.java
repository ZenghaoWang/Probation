package com.teamacademicprobation.probation.player;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Objects;

/**
 * The statistics of a single game, keeping its GameID, date/time of initiation, and statistics.
 * Each instance of a game has its own instance of PlayerGameStats.
 */
public class PlayerGameStats implements Comparable<PlayerGameStats> {

    // The status of the game (whether it is ongoing or complete).
    private boolean isCurrLevel;

    //The time and date of the game's initiation.
    private final Date startTime;

    //The total score in this game (the value of all stats added together)
    private int totalScore;

    private final String gameID;

    // A map of all recorded stats for one instance of a game.
    private final Map<String, Integer> statsMap = new HashMap<>();

    /**
     * Instantiates a PlayerGameStats object with statistics time, score, and items set to 0.
     */
    PlayerGameStats(String id) {
        isCurrLevel = true;
        gameID = id;
        startTime = new Date();
        totalScore = 0;
    }

    /**
     * Returns whether this game is currently being played.
     *
     * @return isCurrLevel
     */
    public boolean isCurrLevel() {
        return isCurrLevel;
    }

    /**
     * Changes this game's status from incomplete to complete.
     */
    void setComplete() {
        isCurrLevel = false;
    }

    /**
     * Returns the date and time this session of the game began.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the given statistic to replace its current score with the given one.
     *
     * @param statID   the statistic to be replaced.
     * @param newValue the new value for the statistic.
     */
    void update(String statID, int newValue) {
        if (statsMap.containsKey(statID)) {
            int currValue = Objects.requireNonNull(statsMap.get(statID));
            updateTotalScore(currValue * (-1)); // subtracts the statistic's previous value from totalScore
        }

        statsMap.put(statID, newValue);
        updateTotalScore(newValue); // adds the statistic's new value to totalScore
    }

    /**
     * Increments a statistic by a given amount. If the statistic doesn't already exist, the key is
     * created in the map with a starting value of the given amount.
     *
     * @param statID   the statistic to be updated
     * @param newValue the value to be added to the statistic (or the value of the stat if it DNE)
     */
    void increment(String statID, int newValue) {
        if (statsMap.containsKey(statID)) {
            int currValue = Objects.requireNonNull(statsMap.get(statID));
            statsMap.put(statID, currValue + newValue);
        } else {
            update(statID, newValue);
        }

        updateTotalScore(newValue);
    }

    /**
     * Updates this game's total score by a given amount.
     *
     * @param score the amount to increment totalScore by.
     */
    private void updateTotalScore(int score) {
        totalScore += score;
    }

    /**
     * Returns this game's ID
     *
     * @return gameID the name of the stat whose data is to be retrieved.
     */
    String getGameID() {
        return gameID;
    }

    /**
     * Returns the score associated with the given statistic.
     *
     * @param statID the name of the stat whose data is to be retrieved.
     */
    int getStat(String statID) {
        return Objects.requireNonNull(statsMap.get(statID));
    }

    /**
     * Returns a map of each statistics and their associated scores for this game.
     */
    Map<String, Integer> getAllStats() {
        return statsMap;
    }

    /**
     * @return this game's total score.
     */
    private int getTotalScore() {
        return totalScore;
    }

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
