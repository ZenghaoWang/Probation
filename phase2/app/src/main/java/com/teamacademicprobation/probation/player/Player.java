package com.teamacademicprobation.probation.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A player for the game.
 */
public class Player {

    private static final int PLAYER_ID_LEN = 5;
    /**
     * The statistics of this player.
     */
    private PlayerStats playerStats;
    /**
     * The username of this player.
     */
    private String username;
    /**
     * The password of this player.
     */
    private String password;
    /**
     * The random playerID of this player
     */
    private String playerID;

    // ===== KEYS FOR THE JSON =====
    static final String USERNAME = "Username";
    static final String PASSWORD = "Password";
    static final String BEST_SESSION = "Best Session";
    static final String CURR_SESSION = "Current Session";
    static final String TOTAL_SESSION = "Total Session";

    Player() {
        this("defaultUser", "defaultPass");
    }

    Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.playerStats = new PlayerStats();
        this.playerID = generateRandomID();
    }

    // ==== SETTER METHODS, CALLED WHEN PlayerBuilder PASSES INFO TO BUILD A PLAYER

    /**
     * Sets the player's current game stats
     *
     * @param currGameStats {{GameID: {StatID: StatValue}}}
     */
    void setCurrStats(Map<String, Map<String, Integer>> currGameStats) {
        this.playerStats.setCurrStats(currGameStats);
    }

    /**
     * Sets the player's best game stats
     *
     * @param bestGameStats {{GameID: {StatID}: StatValue}}
     */
    void setBestStats(Map<String, Map<String, Integer>> bestGameStats) {
        this.playerStats.setBestStats(bestGameStats);
    }

    /**
     * Sets the player's accumulated game stats
     *
     * @param totalGameStats {{GameID: {StatID}: StatValue}}
     */
    void setTotalStats(Map<String, Map<String, Integer>> totalGameStats) {
        this.playerStats.setTotalStats(totalGameStats);
    }

    // Sets the player's username
    void setUsername(String username) {
        this.username = username;
    }

    // Sets the player's password
    void setPassword(String password) {
        this.password = password;
    }

    // Sets the player's ID
    void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    // ==== GETTER METHODS, CALLED BY OTHER CLASSES TO OBTAIN INFO ON Player, ESPECIALLY WHEN SAVING
    // PLAYER INFO

    // Return's playerID
    String getPlayerID() {
        return this.playerID;
    }

    // Return's player's username
    String getUsername() {
        return this.username;
    }

    // Return's player's password
    String getPassword() {
        return this.password;
    } // returns player's password

    // Returns whether a current session for a particular game exists (whether the player is playing
    // this game)
    boolean isBeingPlayed(String gameID) {
        return this.playerStats.isBeingPlayed(gameID);
    }

    /**
     * Returns a map containing the player's current game statistics.
     *
     * @return a map of the player's current game stats in a Map<"GameID", Map<"StatID", Integer>>
     */
    private Map<String, Map<String, Integer>> getCurrStats() {
        return this.playerStats.getCurrStats();
    }

    /**
     * Returns a map containing the current statistics for a particular game.
     *
     * @param gameID the game we want to look at
     * @return a map of the current game stats in a Map<"StatID", Integer>
     */
    Map<String, Integer> getCurrStats(String gameID) {
        return this.playerStats.getCurrStats(gameID);
    }

    /**
     * Returns a map containing the player's best game statistics for every game they've played.
     *
     * @return a map of the player's best game stats in a Map<"GameID", Map<"StatID", Integer>>
     */
    private Map<String, Map<String, Integer>> getBestStats() {
        return this.playerStats.getBestStats();
    }

    /**
     * Returns a map containing the player's best game statistics for a particular game
     *
     * @return a map of the player's best game stats in a Map<"StatID", Integer>
     */
    Map<String, Integer> getBestStats(String gameID) {
        return this.playerStats.getBestStats(gameID);
    }

    /**
     * Returns a map containing the player's accumulated game statistics for every game they've
     * played.
     *
     * @return a map of the player's accumulated game stats in a Map<"GameID", Map<"StatID", Integer>>
     */
    private Map<String, Map<String, Integer>> getTotalStats() {
        return this.playerStats.getTotalStats();
    }

    /**
     * Returns a map containing the player's accumulated game statistics for a particular game.
     *
     * @return a map of the player's accumulated game stats in a Map<"StatID", Integer>
     */
    Map<String, Integer> getTotalStats(String gameID) {
        return this.playerStats.getTotalStats(gameID);
    }

    /**
     * Returns a map of all the information contained in this player
     *
     * @return A map containing info of player in the format of Map<String, Object> to be written into
     * a JSON
     */
    public Map<String, Object> getData() {
        Map<String, Object> result = new HashMap<>();

        // Add username and password
        result.put(USERNAME, this.getUsername());
        result.put(PASSWORD, this.getPassword());


        // Add player's stats
        result.put(CURR_SESSION, this.getCurrStats());
        result.put(BEST_SESSION, this.getBestStats());
        result.put(TOTAL_SESSION, this.getTotalStats());

        return result;
    }

    // Generates a random unique identifier code for the player
    private String generateRandomID() {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = lowercase.toUpperCase();
        String data = uppercase + lowercase;
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < PLAYER_ID_LEN; i++) {
            result.append(data.charAt(random.nextInt(data.length())));
        }
        return result.toString();
    }

    /**
     * Lets you update the player's current game session with a new game.
     *
     * @param currGameID the new game's ID
     */
    void newCurrGame(String currGameID) {
        this.playerStats.newCurrGame(currGameID);
    }

    /**
     * Lets you update the player's current game session with a new game.
     */
    void endCurrGame(String gameID, boolean save) {
        this.playerStats.endCurrGame(gameID, save);
    }


    /**
     * Updates the PlayerStats for the current game.
     *
     * @param statID the statistic to be updated
     * @param value  the new value of the statistic
     */
    void updateCurrStats(String gameID, String statID, int value) {
        this.playerStats.updateCurrGame(gameID, statID, value);
    }

    /**
     * Updates the PlayerStats for the current game.
     *
     * @param gameStatsMap A map with the following format: {"Stat1": int}
     */
    void updateCurrStats(String gameID, Map<String, Integer> gameStatsMap) {
        this.playerStats.updateCurrGame(gameID, gameStatsMap);
    }

    /**
     * Returns a list of all the games the player has played,
     *
     * @return List of gameIDs.
     */
    // Returns a list of all the games the player has played
    List<String> getGamesPlayed() {
        List<String> result = new ArrayList<>();
        for (String gameID : this.playerStats.getBestStats().keySet()) {
            if (!(gameID.equals(""))) {
                result.add(gameID);
            }
        }
        return result;
    }
}
