package com.teamacademicprobation.probation.data;

import android.util.Log;

import com.teamacademicprobation.probation.player.Player;
import com.teamacademicprobation.probation.player.PlayerBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * A class that is responsible for reading and writing files.
 *
 * <p>Each player is stored in a .txt file containing a JSON Object with the following structure:
 *
 * <p>{"PlayerID": {"Username": "playerUsername, "Password": "playerPassword", "Preferences" : {"PreferenceKey" : "thisPreference"},
 * "CurrentSession" : {"GameID" : { "StatID" : "statistic" }}, "BestSession": {"GameID": {"StatID" : "statistic"}}}
 *
 */
public class DataManager implements DataAccessObject {
  /** The DataFile for reading and writing. */
  private File DataFile;

  private static final String TAG = "DataManager";

  public void setData(File dataFile) {
    this.DataFile = dataFile;
  }

  /**
   * Saves the username, password, stats, preferences of the given player.
   *
   * @param player The player object that we want to save.
   */
  public void save(Player player) {
    Map<String, Object> playerDataMap = player.getData();
    JSONObject playerData = new JSONObject(playerDataMap);
    updateFile(playerData, player.getPlayerID());
  }

  /**
   * Overwrites the JSON data file to include the changes to playerID
   *
   * @param playerData The new JSONObject that describes the player.
   * @param playerID The playerID of the player being updated.
   */
  private void updateFile(JSONObject playerData, String playerID) {

    JSONObject JSONdata = readJSON(); // Get the old JSON.
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(DataFile))){ ;
      if(JSONdata == null){
        JSONdata = new JSONObject();
      }
      JSONdata.put(playerID, playerData); // Replaces data for playerID
      writer.write(JSONdata.toString());
    } catch (IOException e) {
      Log.e(TAG, "File not found.");
    } catch (JSONException e) {
      Log.e(TAG, "JSON reading failed.");
    }
  }

  public Player loadPlayer(String playerID){
    JSONObject allPlayersData = readJSON();
    try {
      JSONObject playerData = allPlayersData.getJSONObject(playerID);
      PlayerBuilder playerBuilder = new PlayerBuilder();
      playerBuilder.buildPlayer(playerData, playerID);
      return playerBuilder.getPlayer();

    } catch (JSONException e) {
      Log.e(TAG, "No player with this playerID");
    } catch(NullPointerException e){
      Log.e(TAG, "Null players.");
    }

    return null;
  }

  /**
   * Returns a JSONObject specified from the file.
   *
   * @return JSONObject.
   */
  private JSONObject readJSON() {
    String oldData = readFile();
    JSONObject result = null;
    try {
      result = new JSONObject(oldData);
    } catch (JSONException e) {
      Log.e(TAG, "No data found.");
    }
    return result;
  }

  /**
   * Returns a string from the file specified in DataFile.
   *
   * @return String representation of the file.
   */
  private String readFile() {
    StringBuilder result = new StringBuilder();
    try(FileReader reader = new FileReader(this.DataFile)) {
      BufferedReader br = new BufferedReader(reader);
      String newLine;
      while ((newLine = br.readLine()) != null) {
        result.append(newLine);

      }
    } catch (FileNotFoundException e) {
      Log.e(TAG, "File not found,");
    } catch (IOException e) {
      Log.e(TAG, "Something wrong happened.");
    }
    return result.toString();
  }

  /**
   * Returns the username of a player given their playerID.
   *
   * @param username Username of the player we want to find.
   * @return The playerID of the player with the given username.
   */
  public String getIDfromUsername(String username) {
    JSONObject allPlayersData = readJSON();

    try {
      Iterator<String> keys = allPlayersData.keys();
      while (keys.hasNext()) {
        String curr_gameID = keys.next();
        String curr_username = allPlayersData.getJSONObject(curr_gameID).getString("Username");
        if (curr_username.equals(username)) {
          return curr_gameID;
        }
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error during parsing JSON");
    } catch (NullPointerException e) {
      Log.e(TAG, "No Data.");
    }

    return null;
  }

  public static <T> String getMapToJSONString(Map<String, T> map) {
    JSONObject result = new JSONObject(map);
    return result.toString();
  }

//  public boolean isNewPlayer(String playerID){
//    JSONObject allPlayersData = readJSON();
//    if(allPlayersData == null){
//      return true;
//    }
//      Iterator<String> keys = allPlayersData.keys();
//      while(keys.hasNext()){
//        String currPlayerID = keys.next();
//        if(currPlayerID.equals(playerID)){
//          return false;
//        }
//      }
//    return true;
//
//  }

  public boolean usernameTaken(String username) {
    JSONObject allPlayersData = readJSON();
    if(allPlayersData == null){
      return false;
    }
    try {
      Iterator<String> keys = allPlayersData.keys();
      while (keys.hasNext()) {
        String curr_gameID = keys.next();
        String curr_username = allPlayersData.getJSONObject(curr_gameID).getString("Username");
        if (curr_username.equals(username)) {
          return true;
        }
      }
    } catch (JSONException e) {
      Log.e(TAG, "Error during parsing JSON");
    }
    return false;
  }
}
