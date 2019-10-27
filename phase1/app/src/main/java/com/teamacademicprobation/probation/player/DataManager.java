package com.teamacademicprobation.probation.player;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
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
 * <p>{"PlayerID": {"Username": String, "Password": String, "Preference1": String, "Game1": {Stat1:
 * int}, "PlayerStat1" : int}}
 */
public class DataManager {
  /** The DataFile for reading and writing. */
  private static File DataFile;

  private static final String TAG = "DataManager";

  public static void setDataFile(File dataFile) {
    DataManager.DataFile = dataFile;
  }

  /**
   * Saves the username, password, stats, preferences of the given player.
   *
   * @param player The player object that we want to save.
   */
  public static void save(Player player) {
    Map<String, Object> playerDataMap = player.getData();
    JSONObject playerData = new JSONObject(playerDataMap);
    DataManager.updateFile(playerData, player.getPlayerID());
  }

  /**
   * Overwrites the JSON data file to include the changes to playerID
   *
   * @param playerData The new JSONObject that describes the player.
   * @param playerID The playerID of the player being updated.
   */
  private static void updateFile(JSONObject playerData, String playerID) {
    try (FileWriter file = new FileWriter(DataFile)) {
      JSONObject JSONdata = readJSON(); // Get the old JSON.
      JSONdata.put(playerID, playerData); // Replaces data for playerID
      file.write(JSONdata.toString());
    } catch (IOException e) {
      Log.e(TAG, "File not found.");
    } catch (JSONException e) {
      Log.e(TAG, "JSON reading failed.");
    }
  }

  /**
   * Returns a JSONObject specified from the file.
   *
   * @return JSONObject.
   */
  public static JSONObject readJSON() {
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
  private static String readFile() {
    StringBuilder result = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(DataFile))) {
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
  public static String getIDfromUsername(String username) {
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
    }
    catch(NullPointerException e){
      Log.e(TAG, "No Data.");
    }

    return null;
  }

  public static <T> String getMapToJSONString(Map<String, T> map) {
    JSONObject result = new JSONObject(map);
    return result.toString();
  }
}
