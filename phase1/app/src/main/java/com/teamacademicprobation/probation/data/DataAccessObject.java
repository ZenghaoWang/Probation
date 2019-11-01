package com.teamacademicprobation.probation.data;

import com.teamacademicprobation.probation.player.Player;

import java.io.File;

public interface DataAccessObject {

    void save(Player player);
    Player loadPlayer(String playerID);
    boolean usernameTaken(String username);
    void setData(File dataFile);
    String getIDfromUsername(String username);

}
