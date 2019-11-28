package com.teamacademicprobation.probation.player;

import java.util.List;
import java.util.Map;

public interface PlayerTotalStatsAccess {

  Map<String, Integer> getBestStats(String playerID, String gameID);

  List<String> getGamesPlayed(String playerID);
}
