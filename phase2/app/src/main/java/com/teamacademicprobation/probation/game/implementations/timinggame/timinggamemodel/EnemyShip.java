package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

/**
 * A representation of the enemy ship.
 */
class EnemyShip extends Ship {

    /**
     * The x location of the ship in relation to the whole screen.
     */
    static final double WIDTHRATIO = 0.8;

    /**
     * Initializes a new enemy ship with the default size.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     */
    EnemyShip(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight, WIDTHRATIO);
    }

    /**
     * Initializes a new enemy ship with a specified size.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param size         The side width of the bitmap of the enemy ship.
     */
    EnemyShip(int screenWidth, int screenHeight, int size) {
        super(screenWidth, screenHeight, WIDTHRATIO, size);
    }

}
