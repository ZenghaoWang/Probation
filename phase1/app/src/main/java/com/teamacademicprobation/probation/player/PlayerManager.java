package com.teamacademicprobation.probation.player;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PlayerManager {

  private static final String TAG = "PlayerManager";
  public static Player currentLoggedInPlayer;

  public static boolean createNewPlayer(String username, String password) {
    PlayerBuilder playerBuilder = new PlayerBuilder();
    playerBuilder.buildUserAndPassword(username, password);
    PlayerManager.currentLoggedInPlayer = playerBuilder.getPlayer();
    return true;
  }

/**
 * Returns a player object with their playerID.
 *
 * @param playerID The playerID to find.
 * @return A player with all the data specified in the JSON of the player with playerID.
 */

  public static Player getPlayer(String playerID) {
    JSONObject allPlayersData = DataManager.readJSON();
    try {
      JSONObject playerData = allPlayersData.getJSONObject(playerID);
      PlayerBuilder playerBuilder = new PlayerBuilder();
      playerBuilder.buildPlayerStats(playerData);
      playerBuilder.buildPlayerPreferences(playerData);
      playerBuilder.buildUserAndPassword(playerData);

      return playerBuilder.getPlayer();

    } catch (JSONException e) {
      Log.e(TAG, "No player with this playerID");
    } catch(NullPointerException e){
      Log.e(TAG, "Null players.");
    }

    return null;
  }

  public static boolean login(String username, String password) {
    Player currPlayer = getPlayer(DataManager.getIDfromUsername(username));
    if (currPlayer != null && currPlayer.getPassword().equals(password)) {
      PlayerManager.currentLoggedInPlayer = currPlayer;
      return true;
    } else {
      return false;
    }
  }
}
