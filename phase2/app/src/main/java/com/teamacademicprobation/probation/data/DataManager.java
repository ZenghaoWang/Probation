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
 * <p>{"PlayerID": {"Username": "playerUsername, "Password": "playerPassword", "Preferences" :
 * {"PreferenceKey" : "thisPreference"}, "CurrentSession" : {"GameID" : { "StatID" : "statistic" }},
 * "BestSession": {"GameID": {"StatID" : "statistic"}}}
 */
public class DataManager implements DataAccessObject {
    private static final String TAG = "DataManager";
    /**
     * The data file for reading and writing.
     */
    private File dataFile;

    @Override
    public void setData(File dataFile) {
        this.dataFile = dataFile;
    }

    @Override
    public void save(Player player, String playerID) {
        Map<String, Object> playerDataMap = player.getData();
        JSONObject playerData = new JSONObject(playerDataMap);
        updateFile(playerData, playerID);
    }

    /**
     * Overwrites the JSON data file to include the changes to playerID
     *
     * @param playerData The new JSONObject that describes the player.
     * @param playerID   The playerID of the player being updated.
     */
    private void updateFile(JSONObject playerData, String playerID) {

        JSONObject JSONdata = readJSON(); // Get the old JSON.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            if (JSONdata == null) {
                JSONdata = new JSONObject();
            }
            JSONdata.put(playerID, playerData); // Replaces data for playerID
            writer.write(JSONdata.toString());
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public Player loadPlayer(String playerID) {
        JSONObject allPlayersData = readJSON();
        try {
            JSONObject playerData = allPlayersData.getJSONObject(playerID);
            PlayerBuilder playerBuilder = new PlayerBuilder();
            playerBuilder.buildPlayer(playerData, playerID);
            return playerBuilder.getPlayer();

        } catch (JSONException | NullPointerException e) {
            Log.e(TAG, e.toString());
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
            Log.e(TAG, e.toString());
        }
        return result;
    }

    /**
     * Returns a string from the file specified in dataFile.
     *
     * @return String representation of the file.
     */
    private String readFile() {
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(this.dataFile)) {
            BufferedReader br = new BufferedReader(reader);
            String newLine;
            while ((newLine = br.readLine()) != null) {
                result.append(newLine);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return result.toString();
    }

    @Override
    public String getIDfromUsername(String username) {
        JSONObject allPlayersData = readJSON();
        try {
            Iterator<String> keys = allPlayersData.keys();
            while (keys.hasNext()) {
                String currPlayerID = keys.next();
                String currUsername = allPlayersData.getJSONObject(currPlayerID).getString("Username");
                if (currUsername.equals(username)) {
                    return currPlayerID;
                }
            }
        } catch (JSONException | NullPointerException e) {
            Log.e(TAG, e.toString());
        }

        return null;
    }

    @Override
    public boolean usernameTaken(String username) {
        JSONObject allPlayersData = readJSON();
        if (allPlayersData == null) {
            return false;
        }
        try {
            Iterator<String> keys = allPlayersData.keys();
            while (keys.hasNext()) {
                String currPlayerID = keys.next();
                String currUsername = allPlayersData.getJSONObject(currPlayerID).getString("Username");
                if (currUsername.equals(username)) {
                    return true;
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return false;
    }
}
