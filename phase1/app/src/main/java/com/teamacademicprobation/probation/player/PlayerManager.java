package com.teamacademicprobation.probation.player;


import com.teamacademicprobation.probation.data.DataAccessObject;
import com.teamacademicprobation.probation.data.DataManager;
import java.io.File;

public class PlayerManager implements PlayerAccess{

  private DataAccessObject dataAccess= new DataManager();

  public PlayerManager(){};

  public PlayerManager(File dataFile){
    this.dataAccess.setData(dataFile);
  }

  public String createNewPlayer(String username, String password) {
    if(dataAccess.usernameTaken(username)){
      return null;
    }
    PlayerBuilder playerBuilder = new PlayerBuilder(username, password);
    Player newPlayer = playerBuilder.getPlayer();
    dataAccess.save(newPlayer);
    return newPlayer.getPlayerID();
  }

  @Override
  public void updateStats(String playerID, String gameID, String statID, int stat) {
    Player playerToUpdate = getPlayer(playerID);
    if(!(playerToUpdate.getCurrGameID().equals(gameID))){
        playerToUpdate.endCurrGame();
        playerToUpdate.newCurrGame(gameID);
    }
    playerToUpdate.updateCurrStats(statID, stat);
    dataAccess.save(playerToUpdate);
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

  public String login(String username, String password) {
    Player currPlayer = getPlayer(dataAccess.getIDfromUsername(username));
    if (currPlayer != null && currPlayer.getPassword().equals(password)) {
      return currPlayer.getPlayerID();
    } else {
      return null;
    }
  }

  public void setDataFile(File dataFile){ dataAccess.setData(dataFile);}
}
