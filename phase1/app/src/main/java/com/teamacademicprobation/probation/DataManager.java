package com.teamacademicprobation.probation;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * A class that is responsible for reading and writing files.
 *
 * Each player is stored in a .txt file containing a JSON Object with the following structure:
 *
 * {"PlayerUsername" : {"Password": String "Preference1": String, "Game1Stat1": int,
 * "PlayerStat1": int}
 *
 * OR:
 *
 * {"PlayerID": {"Username": String, "Password": String, "Preference1": String, "Game1Stat1" int,
 * "PlayerStat1" : int}}
 *
 */
public class DataManager {
    /**
     * The DataFile for reading and writing.
     */
    private static File DataFile;

    public static void setDataFile(File dataFile){
        DataManager.DataFile = dataFile;
    }

    /**
     * Saves the username, password, stats, preferences of the given player.
     * @param player
     */
    public static void save(Player player){
        Map<String, Object> playerDataMap = player.getData();
        JSONObject playerData = new JSONObject(playerDataMap);
        DataManager.updateFile(playerData, player.getID());

    }


    // Citation: Code example from
    // https://stackoverflow.com/questions/38041893/json-file-java-editing-updating-fields-values

    /**
     * Overwrites the JSON data file to include the changes to playerID
     * @param playerData The new JSONObject that describes the player.
     * @param playerID The playerID of the player being updated.
     */
    private static void updateFile(JSONObject playerData, String playerID) {
        try{
            FileWriter file = new FileWriter(DataFile);
            JSONObject JSONdata = readJSON(); // Get the old JSON.
            JSONdata.put(playerID, playerData); // Replaces data for playerID
            file.write(JSONdata.toString());
        }
        catch(IOException e){
            Log.e("JSON", "File not found.");
        }
        catch (JSONException e) {
            Log.e("JSON", "JSON reading failed.");
        }

    }

    /**
     * Returns a JSONObject specified from the file.
     * @return JSONObject
     */
    private static JSONObject readJSON() {
        String oldData = readFile();
        JSONObject result = null;
        try {
            result = new JSONObject(oldData);
        } catch (JSONException e) {
            Log.e("JSON", "No data found.");
        }
        return result;
    }

    /**
     * Returns a string from the file specified in DataFile.
     * @return
     */
    private static String readFile() {
        StringBuffer result = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(DataFile))) {
            String newLine;
            while((newLine = br.readLine()) != null){
                result.append(newLine);
            }
        }
        catch(FileNotFoundException e){
            Log.e("JSON", "File not found,");
        }
        catch(IOException e){
            Log.e("JSON", "Something wrong happened.");
        }
        return result.toString();
    }


    // TODO: Loading player data.


    public void getInfo(String username){

    }
}
