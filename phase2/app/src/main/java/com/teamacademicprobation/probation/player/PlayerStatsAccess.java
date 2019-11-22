package com.teamacademicprobation.probation.player;

import java.util.List;
import java.util.Map;

public interface PlayerStatsAccess {

  void updateStats(String playerID, String gameID, String statID, int stat);

  void updateStats(String playerID, String gameID, Map<String, Integer> stats);

  void endGame(String playerID, boolean save);

  Map<String, Integer> getBest(String playerID, String gameID);

  List<String> getGamesPlayed(String playerID);
}
