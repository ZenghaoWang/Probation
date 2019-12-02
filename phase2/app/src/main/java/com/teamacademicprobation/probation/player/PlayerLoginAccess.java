package com.teamacademicprobation.probation.player;

/**
 * An interface that defines the methods that the login screen has access to.
 */
public interface PlayerLoginAccess {

    /**
     * Returns the playerID of the logged in player, if it exists.
     *
     * @param username The username of the player.
     * @param password The password of the player.
     * @return The playerID of the logged in player, of null if login failed.
     */
    String login(String username, String password);

    /**
     * Creates a new player, and saves it into the database.
     *
     * @param username The username of this player.
     * @param password The password of this player.
     * @return The PlayerID of the recently created player, or null if creation failed.
     */
    String createNewPlayer(String username, String password);
}
