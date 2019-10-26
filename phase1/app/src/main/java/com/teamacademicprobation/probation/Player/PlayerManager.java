package com.teamacademicprobation.probation.Player;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

class PlayerManager {

    private static final String TAG = "PlayerManager";

    public static Player createNewPlayer(String username, String password){
        PlayerBuilder playerBuilder = new PlayerBuilder();
        playerBuilder.buildUserAndPassword(username, password);
        return playerBuilder.getPlayer();
    }

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
        }

        return null;
    }

    public static Player login(){}

}
