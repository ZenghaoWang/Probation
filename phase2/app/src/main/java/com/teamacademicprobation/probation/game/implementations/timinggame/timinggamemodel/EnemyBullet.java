package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

/**
 * A bullet shot by the enemy.
 */
class EnemyBullet extends Bullet {

    /**
     * Initializes a new enemy bullet.
     *
     * @param screenWidth  The width of the whole screen, in pixels.
     * @param screenHeight The height of the whole screen, in pixels.
     * @param gameStyle    The style of the game.
     */
    EnemyBullet(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        super(screenHeight, gameStyle);
        this.setX(Math.toIntExact(Math.round(screenWidth * (EnemyShip.WIDTHRATIO))));
        this.setBulletVelocity(Math.toIntExact(Math.round(screenWidth * (PlayerShip.WIDTHRATIO - EnemyShip.WIDTHRATIO) / Bullet.FRAMES)));
    }

}
