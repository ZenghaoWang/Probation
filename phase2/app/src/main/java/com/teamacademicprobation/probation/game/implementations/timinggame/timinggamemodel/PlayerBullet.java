package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

/**
 * A bullet shot by the player.
 */
class PlayerBullet extends Bullet {
    /**
     * Initializes a new player bullet.
     *
     * @param screenWidth  The width of the whole screen, in pixels.
     * @param screenHeight The height of the whole screen, in pixels.
     * @param gameStyle    The style of the game.
     */
    PlayerBullet(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        super(screenHeight, gameStyle);
        this.setX(Math.toIntExact(Math.round(screenWidth * (PlayerShip.WIDTHRATIO))));
        this.setBulletVelocity(
                Math.toIntExact(
                        Math.round(
                                screenWidth * (EnemyShip.WIDTHRATIO - PlayerShip.WIDTHRATIO) / Bullet.FRAMES)));
  }
}
