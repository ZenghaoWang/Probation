package com.teamacademicprobation.probation.player;

import java.util.List;
import java.util.Map;

public interface GameStatsAccess {

  Map<String, Integer> getBestStats(String playerID, String gameID);

  List<String> getGamesPlayed(String playerID);
}
