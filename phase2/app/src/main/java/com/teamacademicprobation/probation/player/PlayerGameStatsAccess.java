package com.teamacademicprobation.probation.player;

import java.util.Map;

public interface PlayerGameStatsAccess {

  void updateStats(String playerID, String gameID, String statID, int stat);

  void updateStats(String playerID, String gameID, Map<String, Integer> stats);

  void endGame(String playerID,String gameID, boolean save);

  Map<String, Integer> getCurrStats(String playerID, String gameID);

  boolean isBeingPlayed(String playerID, String gameID);
}
