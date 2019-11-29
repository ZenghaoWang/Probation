package com.teamacademicprobation.probation.game.implementations.timinggame;


/**
 * An interface for the timingGame to let the model know when to update the playerStats.
 * This happens whenever a bullet strikes an enemy, whenever a new enemy is created, or whenever
 * a power up is selected.
 */
public interface TimingGameListener {

    void notifyListener();
}
