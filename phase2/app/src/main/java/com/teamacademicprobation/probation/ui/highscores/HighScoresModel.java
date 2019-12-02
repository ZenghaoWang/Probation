package com.teamacademicprobation.probation.ui.highscores;

import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerTotalStatsAccess;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The back-end for the high-score screen. Responsible for generation the strings to display.
 */
class HighScoresModel {
	private String playerID;
	
	HighScoresModel(String playerID) {
		this.playerID = playerID;
	}
	
	
	/**
	 * Returns highest scores for each game a player has played
	 *
	 * @return Map of gameId's and the player's highest score
	 */
	public Map<String, String> getBestScores() {
		Map<String, String> scores = new HashMap<>();
		PlayerTotalStatsAccess playerTotalStatsAccess = new PlayerManager();
		List<String> gameIDs = playerTotalStatsAccess.getGamesPlayed(playerID);
		for (String gameID : gameIDs) {
			Map<String, Integer> gameStatMap = playerTotalStatsAccess.getBestStats(playerID, gameID);
			int score = gameStatMap.get(ScoreScreenActivity.SCORE_KEY); //TODO make SCORE all caps for all games
			scores.put(gameID, Integer.toString(score));
		}
		return scores;
	}
	
	
	/**
	 * Returns total scores for each game a player has played
	 *
	 * @return Map of gameId's and the player's total score
	 */
	public Map<String, String> getTotalScores() {
		Map<String, String> scores = new HashMap<>();
		PlayerTotalStatsAccess playerTotalStatsAccess = new PlayerManager();
		List<String> gameIDs = playerTotalStatsAccess.getGamesPlayed(playerID);
		for (String gameID : gameIDs) {
			Map<String, Integer> gameStatMap = playerTotalStatsAccess.getTotalStats(playerID, gameID);
			int score = gameStatMap.get(ScoreScreenActivity.SCORE_KEY); //TODO make SCORE all caps for all games
			scores.put(gameID, Integer.toString(score));
		}
		return scores;
	}
}
