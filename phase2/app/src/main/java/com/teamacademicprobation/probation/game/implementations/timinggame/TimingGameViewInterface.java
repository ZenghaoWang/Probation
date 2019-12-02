package com.teamacademicprobation.probation.game.implementations.timinggame;

/**
 * An interface that determines what TimingGameViews should be able to do.
 */
public interface TimingGameViewInterface {
    /**
     * Goes the the score screen. This method is called once the game is over.
     *
     * @param score    The score to be displayed.
     * @param playerID The playerID of the current game.
     * @param gameID   The gameID of this current game.
     */
    void goToScoreScreen(String score, String playerID, String gameID);
}
