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
     * The preferences of this player.
     */
    private PlayerPreferences playerPreferences;
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


    // ===== KEYS FOR THE JSON
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
        this.playerPreferences = new PlayerPreferences();
        this.playerID = generateRandomID();
    }

    // ==== SETTER METHODS, CALLED WHEN PlayerBuilder PASSES INFO TO BUILD A PLAYER

    void setCurrStats(Map<String, Map<String, Integer>> currGameStats) {
        this.playerStats.setCurrStats(currGameStats);
    }

    void setBestStats(Map<String, Map<String, Integer>> bestGameStats) {
        this.playerStats.setBestStats(bestGameStats);
    }

    void setTotalStats(Map<String, Map<String, Integer>> totalGameStats) {
        this.playerStats.setTotalStats(totalGameStats);
    }

    String getUsername() {
        return this.username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return this.password;
    } // returns player's password

    void setPassword(String password) {
        this.password = password;
    }

    String getPlayerID() {
        return this.playerID;
    } // returns player's ID

    // ==== GETTER METHODS, CALLED BY OTHER CLASSES TO OBTAIN INFO ON Player, ESPECIALLY WHEN SAVING
    // PLAYER INFO

    void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    /**
     * Returns a map of the player's preferences
     *
     * @return player's preferences in a Map<String, String>
     */
    Map<String, String> getPreferences() {
        return this.playerPreferences.getPreferences();
    }

    void setPreferences(Map<String, String> playerPreferences) {
        this.playerPreferences.setPreferences(playerPreferences);
    }

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

    Map<String, Integer> getCurrStats(String gameID) {
        return this.playerStats.getCurrStats(gameID);
    }

    /**
     * Returns a map containing the player's best game statistics.
     *
     * @return a map of the player's best game stats in a Map<"GameID", Map<"StatID", Integer>>
     */
    Map<String, Integer> getBestStats(String gameID) {
        return this.playerStats.getBestStats(gameID);
    }

    /**
     * Returns a map containing the player's best game statistics.
     *
     * @return a map of the player's best game stats in a Map<"GameID", Map<"StatID", Integer>>
     */
    private Map<String, Map<String, Integer>> getBestStats() {
        return this.playerStats.getBestStats();
    }

    Map<String, Integer> getTotalStats(String gameID) {
        return this.playerStats.getTotalStats(gameID);
    }

    private Map<String, Map<String, Integer>> getTotalStats() {
        return this.playerStats.getTotalStats();
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

        // Add player preferences
        result.put("Preferences", this.getPreferences());

        // Add player's stats
        result.put(CURR_SESSION, this.getCurrStats());
        result.put(BEST_SESSION, this.getBestStats());
        result.put(TOTAL_SESSION, this.getTotalStats());

        return result;
    }


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
     * Updates PlayerPreferences.
     */
    void updatePreferences(String preferenceKey, String preferenceSetting) {
        this.playerPreferences.updatePreferences(preferenceKey, preferenceSetting);
    }

    /**
     * Updates PlayerPreferences.
     *
     * @param newPreferences A map with the following format: {gameID: {PreferenceID: Preference
     *                       value}}
     */
    void updatePreferences(Map<String, String> newPreferences) {
        this.playerPreferences.updatePreferences(newPreferences);
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
