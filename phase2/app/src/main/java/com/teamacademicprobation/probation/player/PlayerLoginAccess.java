package com.teamacademicprobation.probation.player;

public interface PlayerLoginAccess {

    String login(String username, String password);

    String createNewPlayer(String username, String password);

}
