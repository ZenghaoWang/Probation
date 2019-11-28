package com.teamacademicprobation.probation.player;

import java.util.Map;

public interface PlayerGameStatsAccess {

  void updateStats(String playerID, String gameID, String statID, int stat);

  void updateStats(String playerID, String gameID, Map<String, Integer> stats);

  void endGame(String playerID, boolean save);

  String getCurrGameID(String playerID);

  Map<String, Integer> getCurrStats(String playerID);
}
