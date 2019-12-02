package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

/**
 * A representation of the player's ship.
 */
class PlayerShip extends Ship {

    /**
     * The x location of the ship in relation to the whole screen.
     */
    static final double WIDTHRATIO = 0.15;

    /**
     * Initializes a new player ship with the default size.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     */
    PlayerShip(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight, WIDTHRATIO);
    }
}
