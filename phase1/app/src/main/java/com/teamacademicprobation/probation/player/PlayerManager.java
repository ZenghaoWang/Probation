package com.teamacademicprobation.probation.player;


import com.teamacademicprobation.probation.data.PlayerDataAccess;
import com.teamacademicprobation.probation.data.DataManager;
import java.io.File;

public class PlayerManager {
  private static Player currentLoggedInPlayer;

  private static PlayerDataAccess dataAccess= new DataManager();


  public static boolean createNewPlayer(String username, String password) {
    if(dataAccess.usernameTaken(username)){
      return false;
    }
    PlayerBuilder playerBuilder = new PlayerBuilder(username, password);
    PlayerManager.currentLoggedInPlayer = playerBuilder.getPlayer();
    dataAccess.save(currentLoggedInPlayer);
    return true;
  }

/**
 * Returns a player object with their playerID.
 *
 * @param playerID The playerID to find.
 * @return A player with all the data specified in the JSON of the player with playerID.
 */

  private static Player getPlayer(String playerID) {
    return dataAccess.loadPlayer(playerID);
  }

  public static boolean login(String username, String password) {
    Player currPlayer = getPlayer(dataAccess.getIDfromUsername(username));
    if (currPlayer != null && currPlayer.getPassword().equals(password)) {
      PlayerManager.currentLoggedInPlayer = currPlayer;
      return true;
    } else {
      return false;
    }
  }

  public static Player getCurrentLoggedInPlayer(){ return currentLoggedInPlayer;}

  public static void setDataFile(File dataFile){ dataAccess.setData(dataFile);}
}
