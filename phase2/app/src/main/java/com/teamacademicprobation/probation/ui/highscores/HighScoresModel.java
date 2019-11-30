package com.teamacademicprobation.probation.ui.highscores;

import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerTotalStatsAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class HighScoresModel {
    private String playerID;

    HighScoresModel(String playerID) {
        this.playerID = playerID;
    }

    /**
     * Gets the Strings that represent the scores of each game.
     *
     * @return A String Array that contains the string representations of each game.
     */
     String[] getScoreStrings() {

        String[] result;
        PlayerTotalStatsAccess playerTotalStatsAccess = new PlayerManager();
        List<String> statsToShow = new ArrayList<>();
        List<String> gameIDs = playerTotalStatsAccess.getGamesPlayed(playerID);
        for (String gameID : gameIDs) {
            buildStrings(playerID, playerTotalStatsAccess, statsToShow, gameID);
        }
        result = new String[statsToShow.size()];
        statsToShow.toArray(result);
        return result;
    }

    /**
     * A helper method that builds the string that represent each game and it's statistics, and
     * appends it to the list statsToShow.
     *
     * @param playerID The playerID of this game.
     * @param playerTotalStatsAccess An object that allows some access into the player.
     * @param statsToShow The list to append the results onto.
     * @param gameID The gameID of the game to retrieve statistics from.
     */
    private void buildStrings(
            String playerID,
            PlayerTotalStatsAccess playerTotalStatsAccess,
            List<String> statsToShow,
            String gameID) {
        StringBuilder toShow = new StringBuilder(gameID + ": ");
        Map<String, Integer> gameStatMap = playerTotalStatsAccess.getBestStats(playerID, gameID);
        for (String statID : gameStatMap.keySet()) {
            toShow.append(statID).append(": ").append(gameStatMap.get(statID));
        }
        statsToShow.add(toShow.toString());
    }
}
