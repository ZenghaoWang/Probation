package com.teamacademicprobation.probation.game.implementations.timinggame;


/**
 * An interface for the timingGame to let the model know when to update the playerStats, as well as
 * to let the model know when it is completed.
 * Player stats should be updated whenever a bullet strikes, whenever a new enemy is
 * created, or whenever a power up is selected.
 */
public interface TimingGameListener {
    /**
     * Notifies the listener about changes to the game.
     */
    void notifyChange();

    /**
     * Notifies the listener that the timing game is complete.
     */
    void notifyComplete();
}
