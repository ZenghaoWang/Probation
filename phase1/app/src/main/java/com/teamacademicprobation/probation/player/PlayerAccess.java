package com.teamacademicprobation.probation.player;

import java.io.File;
import java.util.List;
import java.util.Map;

/** An interface that defines the methods available for use to access and write to player data. */
public interface PlayerAccess {
  String login(String username, String password);

  String createNewPlayer(String username, String password);

  void updateStats(String playerID, String gameID, String statID, int stat);

  Map<String, Integer> getBest(String playerID, String gameID);

  List<String> getGamesPlayed(String playerID);
}
