package com.teamacademicprobation.probation.player;

import android.util.Log;

import com.teamacademicprobation.probation.data.DataAccessObject;
import com.teamacademicprobation.probation.data.DataManager;
import java.io.File;
import java.util.List;
import java.util.Map;

/** An implementation of PlayerAccess. */
public class PlayerManager implements PlayerLoginAccess, PlayerStatsAccess, PlayerPreferencesAccess {

  /** The DataAccessObject that will be responsible for writing/reading from the database. */
  private static DataAccessObject dataAccess = new DataManager();

  /**
   * Creates a new player, and saves it into the database.
   *
   * @param username The username of this player.
   * @param password The password of this player.
   * @return
   */
  @Override
  public String createNewPlayer(String username, String password) {
    if (dataAccess.usernameTaken(username)) {
      return null;
    }
    PlayerBuilder playerBuilder = new PlayerBuilder(username, password);
    Player newPlayer = playerBuilder.getPlayer();
    dataAccess.save(newPlayer);
    return newPlayer.getPlayerID();
  }

  /**
   * Updates the statistics of a player.
   *
   * @param playerID The playerID of the player to save into.
   * @param gameID The game that calls this method.
   * @param statID The stat that the game wants to save.
   * @param stat The stat value.
   */
  @Override
  public void updateStats(String playerID, String gameID, String statID, int stat) {

    // Right now this code doesn't work if we want to update more than one stat, since
    // The precondition is that it is called once iff the game has ended.
    // We have to fix this in phase 2 so we can update more statistics.
    // We can overload this method to accept a Map instead of statID and stat.

    try {
      Player playerToUpdate = getPlayer(playerID);
      if (!(playerToUpdate.getCurrGameID().equals(gameID))) {

        // pre-condition: the current game is none.
        playerToUpdate.newCurrGame(gameID);
      }
      playerToUpdate.updateCurrStats(statID, stat);
      playerToUpdate.endCurrGame();
      dataAccess.save(playerToUpdate);
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
   * Returns a map of the best scores for a given game, given a player.
   *
   * @param playerID the playerID to retrieve information from.
   * @param gameID the gameID of the best scores to retrieve.
   * @return
   */
  @Override
  public Map<String, Integer> getBest(String playerID, String gameID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getBest(gameID);
  }

  /**
   * Returns a list of all the games this player has played.
   *
   * @param playerID
   * @return
   */
  @Override
  public List<String> getGamesPlayed(String playerID) {
    Player currPlayer = getPlayer(playerID);
    return currPlayer.getGamesPlayed();
  }

  /**
   * Sets the data file.
   *
   * @param dataFile
   */
  public static void setDataFile(File dataFile) {
    dataAccess.setData(dataFile);
  }
}
