package com.teamacademicprobation.probation.player;

import android.util.Log;

import com.teamacademicprobation.probation.data.DataAccessObject;
import com.teamacademicprobation.probation.data.DataManager;

import java.io.File;
import java.util.List;
import java.util.Map;

/** An implementation of PlayerAccess. */
public class PlayerManager
    implements PlayerLoginAccess,
        PlayerGameStatsAccess,
        PlayerPreferencesAccess,
        PlayerTotalStatsAccess {

  /** The DataAccessObject that will be responsible for writing/reading from the database. */
  private static DataAccessObject dataAccess = new DataManager();

  /**
   * Sets the data file.
   *
   * @param dataFile
   */
  public static void setDataFile(File dataFile) {
    dataAccess.setData(dataFile);
  }

  /**
   * Creates a new player and generates a unique player ID for them
   *
   * @param username The username of this player.
   * @param password The password of this player.
   * @return the ID of the new player
   */
  @Override
  public String createNewPlayer(String username, String password) {
    if (dataAccess.usernameTaken(username)) {
      return null;
    }
    PlayerBuilder playerBuilder = new PlayerBuilder(username, password);
    Player newPlayer = playerBuilder.getPlayer();
    dataAccess.save(newPlayer, newPlayer.getPlayerID());
    return newPlayer.getPlayerID();
  }

  /**
   * Updates the statistics of a player (a single statistic)
   *
   * @param playerID The playerID of the player to save into.
   * @param gameID The game that calls this method.
   * @param statID The stat that the game wants to save.
   * @param stat The stat value.
   */
  @Override
  public void updateStats(String playerID, String gameID, String statID, int stat) {

    try {
      Player playerToUpdate = getPlayer(playerID);
      if (!(playerToUpdate.isBeingPlayed(gameID))) {

        // pre-condition: the current game is none.
        playerToUpdate.newCurrGame(gameID);
      }
      playerToUpdate.updateCurrStats(gameID, statID, stat);
      dataAccess.save(playerToUpdate, playerID);
    } catch (NullPointerException e) {
      Log.e("PlayerManager", "No player with this playerID.");
    }
  }

  /**
   * Updates the statistics of a player (a map of statistics)
   *
   * @param playerID The playerID of the player to save into.
   * @param gameID The game that calls this method.
   * @param stats A map of the statistics plus their values
   */
  @Override
  public void updateStats(String playerID, String gameID, Map<String, Integer> stats) {

    // Updates multiple stats (a map of statIDs plus their values)

    try {
      Player playerToUpdate = getPlayer(playerID);
      if (!(playerToUpdate.isBeingPlayed(gameID))) {

        // pre-condition: the current game is none.
        playerToUpdate.newCurrGame(gameID);
      }
      playerToUpdate.updateCurrStats(gameID, stats);
      dataAccess.save(playerToUpdate, playerID);
    } catch (NullPointerException e) {
      Log.e("PlayerManager", "No player with this playerID.");
    }
  }

  /**
   * Alerts the player that their current game is over
   *
   * @param playerID
   * @param save
   */
  @Override
  public void endGame(String playerID, String gameID, boolean save) {
    try {
      Player playerToUpdate = getPlayer(playerID);
      if (playerToUpdate.isBeingPlayed(gameID)) {

        // pre-condition: the current game is none.
        playerToUpdate.endCurrGame(gameID, save);
      }
      dataAccess.save(playerToUpdate, playerID);
    } catch (NullPointerException e) {
      Log.e("PlayerManager", "No player with this playerID.");
    }
  }

  /**
   * Returns a player object with their playerID.
   *
   * @param playerID The playerID to find.
   * @return A player with all the data specified in the JSON of the player with playerID.
   */
  private Player getPlayer(String playerID) {
    return dataAccess.loadPlayer(playerID);
  }

  /**
   * Returns the playerID of the logged in player, if it exists.
   *
   * @param username The username of the player.
   * @param password The password of the player.
   * @return
   */
  public String login(String username, String password) {
    Player currPlayer = getPlayer(dataAccess.getIDfromUsername(username));
    if (currPlayer != null && currPlayer.getPassword().equals(password)) { // Top notch security.
      return currPlayer.getPlayerID();
    } else {
      return null;
    }
  }

  /**
   * Returns whether the player is playing a specific game.
   *
   * @param playerID The player to check.
   * @param gameID The game to check.
   * @return
   */
  @Override
  public boolean isBeingPlayed(String playerID, String gameID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.isBeingPlayed(gameID);
  }

  /**
   * Returns a map of the best scores for a given game, given a player.
   *
   * @param playerID the playerID to retrieve information from.
   * @param gameID the gameID of the best scores to retrieve.
   * @return
   */
  @Override
  public Map<String, Integer> getBestStats(String playerID, String gameID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getBestStats(gameID);
  }

  /**
   * Returns the accumulated stats for a specific game
   *
   * @param playerID The player to get the total stats for.
   * @param gameID The game to get the total stats for.
   * @return
   */
  @Override
  public Map<String, Integer> getTotalStats(String playerID, String gameID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getTotalStats(gameID);
  }

  /**
   * Returns all the current stats for a specific game
   *
   * @param playerID The player to get stats from.
   * @param gameID The game to get stats from.
   * @return
   */
  @Override
  public Map<String, Integer> getCurrStats(String playerID, String gameID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getCurrStats(gameID);
  }

  /**
   * Returns a list of all the games this player has played.
   *
   * @param playerID the ID of the player
   * @return a List<String> of all the game IDs for games they've played
   */
  @Override
  public List<String> getGamesPlayed(String playerID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getGamesPlayed();
  }

  /**
   * Updates the player's preferences (a single preference)
   *
   * @param playerID the ID of the player
   * @param preferenceKey the particular preference to be updated
   * @param preferenceSetting the new value of that preference setting
   */
  @Override
  public void updatePreferences(String playerID, String preferenceKey, String preferenceSetting) {
    Player playerToUpdate = getPlayer(playerID);
    playerToUpdate.updatePreferences(preferenceKey, preferenceSetting);
  }

  /**
   * Updates the player's preferences (a map of preferences)
   *
   * @param playerID the ID of the player
   * @param preferences a map of preferences with their new values
   */
  @Override
  public void updatePreferences(String playerID, Map<String, String> preferences) {
    Player playerToUpdate = getPlayer(playerID);
    playerToUpdate.updatePreferences(preferences);
  }
}
