package com.teamacademicprobation.probation.player;

import java.io.File;

public interface PlayerAccess {
    String login(String username, String password);
    String createNewPlayer(String username, String password);
    void updateStats(String playerID, String gameID, String statID, int stat);
}
