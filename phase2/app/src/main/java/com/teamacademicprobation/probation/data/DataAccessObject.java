package com.teamacademicprobation.probation.data;

import com.teamacademicprobation.probation.player.Player;

import java.io.File;

/**
 * An interface that defines the methods that all objects that access data must implement.
 */
public interface DataAccessObject {

    /**
     * Saves the username, password, stats, preferences of the given player.
     *
     * @param player The player object that we want to save.
     * @param playerID The player ID of the player being saved.
     */
    void save(Player player, String playerID);

    /**
     * Instantiates a player object that specifies to the data stored for playerID
     *
     * @param playerID The playerID of the player object to be created
     * @return New player object with appropriate data.
     */
    Player loadPlayer(String playerID);

    /**
     * Returns true is the username exists in the database.
     *
     * @param username The username to be checked.
     * @return true is there is a player with the same username.
     */
    boolean usernameTaken(String username);

    /**
     * Sets the file to save to.
     *
     * @param dataFile The file to save to.
     */
    void setData(File dataFile);

    /**
     * Returns the username of a player given their playerID.
     *
     * @param username Username of the player we want to find.
     * @return The playerID of the player with the given username.
     */
    String getIDfromUsername(String username);
}
